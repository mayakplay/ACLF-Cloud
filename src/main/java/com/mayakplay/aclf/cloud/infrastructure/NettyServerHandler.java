package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayClientInfo;
import com.mayakplay.aclf.cloud.stereotype.Nugget;
import com.mayakplay.aclf.cloud.util.JsonUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@ChannelHandler.Sharable
final class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private GatewayClientsContainer gatewayContainer;

    NettyServerHandler(GatewayClientsContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("DBG:" + msg);

        final NuggetWrapper nuggetWrapper = JsonUtils.toObject((String) msg, NuggetWrapper.class);

        if (nuggetWrapper != null) {
            Nugget response = gatewayContainer.onRead(ctx, ctx.channel().remoteAddress(), nuggetWrapper);

            if (response != null) {
                ctx.writeAndFlush(JsonUtils.toJson(response) + "\n");
            }
        }

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final boolean allowedClient = gatewayContainer.onConnect(ctx.channel().remoteAddress());

        if (!allowedClient) {
            ctx.close();
        }

        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        gatewayContainer.onDisconnect(ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }

    void sendToClient(@NotNull GatewayClientInfo clientInfo, @NotNull Nugget nugget) {
        final ChannelHandlerContext contextByClientId = gatewayContainer.getContextByClientId(clientInfo.getId());

        if (contextByClientId != null) {
            final String jsonNugget = JsonUtils.toJson(nugget);

            contextByClientId.writeAndFlush(JsonUtils.toJson(jsonNugget) + "\n");
        }
    }

    void sendToAll(@NotNull Nugget nugget) {
        for (ChannelHandlerContext context : gatewayContainer.getContainedContexts()) {
            context.writeAndFlush(JsonUtils.toJson(nugget) + "\n");
        }
    }

}