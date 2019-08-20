package com.mayakplay.aclf.cloud.infrastructure;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @author mayakplay
 * @since 20.08.2019.
 */
class ReconnectConnectionListener implements ChannelFutureListener {

    private final NettyClientThread clientThread;

    ReconnectConnectionListener(NettyClientThread clientThread) {
        this.clientThread = clientThread;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            System.out.println("[ACLF-Cloud] Reconnect");

            final EventLoop loop = channelFuture.channel().eventLoop();

            loop.schedule(() -> { clientThread.createBootstrap(new Bootstrap(), loop);
            }, 2L, TimeUnit.SECONDS);
        }
    }
}