package com.mayakplay.aclf.cloud.infrastructure;

import com.google.common.collect.ImmutableMap;
import com.mayakplay.aclf.cloud.nugget.RegisterMessage;
import com.mayakplay.aclf.cloud.nugget.RegisterResponseMessage;
import com.mayakplay.aclf.cloud.stereotype.GatewayInfo;
import com.mayakplay.aclf.cloud.stereotype.Nugget;
import com.mayakplay.aclf.cloud.stereotype.NuggetReceiveCallback;
import com.mayakplay.aclf.cloud.stereotype.RegistrationCallback;
import com.mayakplay.aclf.cloud.util.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@RequiredArgsConstructor
final class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, String> parameters;

    private final NuggetReceiveCallback callback;
    private final RegistrationCallback registrationCallback;
    private final String clientType;

    private ChannelHandlerContext context;
    private GatewayInfo gatewayInfo;

    void sendNugget(Nugget nugget) {
        if (context != null && gatewayInfo != null) {
            context.writeAndFlush(JsonUtils.toJson(nugget) + "\n");
        }
    }

    boolean isRegistered() {
        return gatewayInfo != null;
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        this.context = context;

        final NuggetWrapper test = new NuggetWrapper(JsonUtils.toJson(new RegisterMessage(clientType, parameters)), new HashMap<>());

        final String json = JsonUtils.toJson(test);

        context.channel().writeAndFlush(json + "\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        final String messageString = (String) msg;

        final NuggetWrapper nugget = JsonUtils.toObject(messageString, NuggetWrapper.class);

        if (nugget != null) {

            if (gatewayInfo == null) {
                final RegisterResponseMessage responseMessage =
                        JsonUtils.toObject(nugget.getMessage(), RegisterResponseMessage.class);

                if (responseMessage != null) {
                    final String clientId = responseMessage.getClientId();

                    gatewayInfo = new RegisteredGatewayInfo(clientId);
                    registrationCallback.onRegister(gatewayInfo, ImmutableMap.copyOf(parameters));
                }
            } else {
                callback.nuggetReceived(nugget);
            }
        }

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