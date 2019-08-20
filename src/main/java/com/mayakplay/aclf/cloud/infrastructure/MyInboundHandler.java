package com.mayakplay.aclf.cloud.infrastructure;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author mayakplay
 * @since 20.08.2019.
 */
public class MyInboundHandler extends SimpleChannelInboundHandler {

    private final NettyClientThread clientThread;

    public MyInboundHandler(NettyClientThread clientThread) {
        this.clientThread = clientThread;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> {
            clientThread.createBootstrap(new Bootstrap(), eventLoop);
        }, 1L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}