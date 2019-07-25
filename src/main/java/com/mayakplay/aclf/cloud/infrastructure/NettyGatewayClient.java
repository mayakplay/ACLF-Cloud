package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public final class NettyGatewayClient implements GatewayClient {

    private final NettyClientThread nettyClientThread;
    private final List<NuggetReceiveCallback> receiveCallbacks = new ArrayList<>();
    private final List<RegistrationCallback> registrationCallbacks = new ArrayList<>();

    public NettyGatewayClient(String host, int port, String clientType, @NotNull Map<String, String> parameters) {
        this.nettyClientThread = new NettyClientThread(host, port, clientType, parameters, this::initReceiveCallbacks, this::initRegistrationCallbacks);
        this.nettyClientThread.start();
    }

    private void initReceiveCallbacks(Nugget nugget) {
        receiveCallbacks.forEach(callback -> callback.nuggetReceived(nugget));
    }

    private void initRegistrationCallbacks(GatewayInfo gatewayInfo, Map<String, String> parameters) {
        registrationCallbacks.forEach(callback -> callback.onRegister(gatewayInfo, parameters));
    }

    @Override
    public GatewayClient addReceiveCallback(NuggetReceiveCallback callback) {
        receiveCallbacks.add(callback);
        return this;
    }

    @Override
    public GatewayClient addRegistrationCallback(RegistrationCallback callback) {
        registrationCallbacks.add(callback);
        return this;
    }

    @Override
    public boolean isRegistered() {
        return nettyClientThread.isRegistered();
    }

    @Override
    public void sendNugget(String nuggetMessage, Map<String, String> parameters) {
        nettyClientThread.sendToServer(nuggetMessage, parameters);
    }

}