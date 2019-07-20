package com.mayakplay.aclf.cloud.infrastructure;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public final class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    public void sendMessage(String message) {
        if (context != null) {
            final ChannelFuture channelFuture = context.writeAndFlush(Unpooled.copiedBuffer(message + "\n", CharsetUtil.UTF_8));
            if (channelFuture.isSuccess()) {
                System.out.println("message sent");
            }
        }
    }

    /**
     * Creates a client-side handler.
     */
    @Override
    public void channelActive(ChannelHandlerContext context) {
        this.context = context;
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        super.channelInactive(context);
    }

}