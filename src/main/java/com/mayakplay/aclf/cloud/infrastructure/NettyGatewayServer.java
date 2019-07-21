package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import com.mayakplay.aclf.cloud.stereotype.GatewayClientInfo;
import com.mayakplay.aclf.cloud.stereotype.ClientNuggetReceiveCallback;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public final class NettyGatewayServer implements GatewayServer {

    private final NettyServerThread nettyServerThread;

    public NettyGatewayServer(int port, Set<String> allowedIps, ClientNuggetReceiveCallback receiveCallback) {
        nettyServerThread = new NettyServerThread(port, allowedIps, receiveCallback);
        nettyServerThread.start();
    }

    @Override
    public @NotNull Map<String, GatewayClientInfo> getClients() {
        return nettyServerThread.getClients();
    }

    @Override
    public @NotNull Map<String, GatewayClientInfo> getClientsByType(String type) {
        return nettyServerThread.getClientsByType(type);
    }

    @Override
    public @Nullable GatewayClientInfo getClientById(String clientId) {
        return nettyServerThread.getClientById(clientId);
    }

    @Override
    public void sendToClient(@NotNull GatewayClientInfo clientInfo, @NotNull String message, @NotNull Map<String, String> params) {
        nettyServerThread.sendToClient(clientInfo, message, params);
    }

    @Override
    public void sendToAll(@NotNull String message, @NotNull Map<String, String> params) {
        nettyServerThread.sendToAll(message, params);
    }

}