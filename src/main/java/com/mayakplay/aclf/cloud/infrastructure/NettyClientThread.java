package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.NuggetReceiveCallback;
import com.mayakplay.aclf.cloud.stereotype.RegistrationCallback;
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

    private final EventLoopGroup loopGroup = new NioEventLoopGroup();

    private final String host;
    private final int port;
    private final NettyClientHandler nettyClientHandler;

    NettyClientThread(String host, int port, String clientType, Map<String, String> parameters, NuggetReceiveCallback receiveCallback, RegistrationCallback registrationCallback) {
        this.nettyClientHandler = new NettyClientHandler(parameters, receiveCallback, registrationCallback, clientType);
        this.host = host;
        this.port = port;
    }

    Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        if (bootstrap != null) {
            final NettyClientThread clientThread = this;

            final ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel) {
                    ChannelPipeline channelPipeline = socketChannel.pipeline();
                    channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()));
                    channelPipeline.addLast("decoder", new StringDecoder());
                    channelPipeline.addLast("encoder", new StringEncoder());
                    channelPipeline.addLast(nettyClientHandler);
                    channelPipeline.addLast(new ReconnectInboundHandler(clientThread));
                }
            };


            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(channelInitializer);
            bootstrap.remoteAddress(host, port);
            bootstrap.connect().addListener(new ReconnectConnectionListener(this));
        }

        return bootstrap;
    }

    @Override
    public void run() {
        createBootstrap(new Bootstrap(), loopGroup);
//        final ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {
//            @Override
//            public void initChannel(SocketChannel socketChannel) {
//                ChannelPipeline channelPipeline = socketChannel.pipeline();
//                channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()));
//                channelPipeline.addLast("decoder", new StringDecoder());
//                channelPipeline.addLast("encoder", new StringEncoder());
//                channelPipeline.addLast(nettyClientHandler);
//            }
//        };
//
//        // Configure the client.
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(group)
//                    .channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
//                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .handler(channelInitializer);
//
//            //region OLD
////            // Start the client.
////            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
////
////            // Wait until the connection is closed.
////            channelFuture.channel().closeFuture().sync();
//            //endregion
//
//            // Start the client.
//            ChannelFuture channelFuture = bootstrap.connect(host, port)
//                    .channel().closeFuture();
////                    .sync();
////
//            // Wait until the connection is closed.
//            channelFuture.channel().closeFuture().sync();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // Shut down the event loop to terminate all threads.
//            group.shutdownGracefully();
//        }
    }

    void sendToServer(String message, Map<String, String> parameters) {
        if (isRegistered())
            nettyClientHandler.sendNugget(new NuggetWrapper(message, parameters));
    }

    boolean isRegistered() {
        return nettyClientHandler.isRegistered();
    }

}

