package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayClient;
import com.mayakplay.aclf.cloud.stereotype.Nugget;
import com.mayakplay.aclf.cloud.stereotype.NuggetReceiveCallback;

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

    public NettyGatewayClient(String host, int port) {
        this.nettyClientThread = new NettyClientThread(host, port, this::initCallbacks);
    }

    public void addReceiveCallback(NuggetReceiveCallback callback) {
        receiveCallbacks.add(callback);
    }

    private void initCallbacks(Nugget nugget) {
        receiveCallbacks.forEach(callback -> callback.nuggetReceived(nugget));
    }

    public boolean isRegistered() {
        return nettyClientThread.isRegistered();
    }

    @Override
    public void sendNugget(String nuggetMessage, Map<String, String> parameters) {
        nettyClientThread.sendToServer(nuggetMessage, parameters);
    }

}