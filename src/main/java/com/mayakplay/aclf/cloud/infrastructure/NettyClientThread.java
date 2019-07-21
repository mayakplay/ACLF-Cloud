package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.NuggetReceiveCallback;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
final class NettyClientThread extends Thread {

    private final String host;
    private final int port;
    private final NettyClientHandler nettyClientHandler;

    NettyClientThread(String host, int port, NuggetReceiveCallback receiveCallback) {
        this.nettyClientHandler = new NettyClientHandler(receiveCallback);
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        final ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) {
                ChannelPipeline channelPipeline = socketChannel.pipeline();
                channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                channelPipeline.addLast("decoder", new StringDecoder());
                channelPipeline.addLast("encoder", new StringEncoder());
                channelPipeline.addLast(nettyClientHandler);
            }
        };

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(channelInitializer);

            // Start the client.
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            // Wait until the connection is closed.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    void sendToServer(String message, Map<String, String> parameters) {
        nettyClientHandler.sendNugget(new NuggetWrapper(message, parameters));
    }

    boolean isRegistered() {
        return nettyClientHandler.isRegistered();
    }

}
