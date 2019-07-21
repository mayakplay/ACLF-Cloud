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
public class NettyGatewayServer implements GatewayServer {

    private final NettyServerThread nettyServerThread;
    private final Set<String> allowedIps;

    public NettyGatewayServer(int port, Set<String> allowedIps, ClientNuggetReceiveCallback receiveCallback) {
        this.allowedIps = allowedIps;
        nettyServerThread = new NettyServerThread(port, receiveCallback);
        nettyServerThread.start();
    }

    @Override
    public @NotNull Map<String, GatewayClientInfo> getClients() {
        return null;
    }

    @Override
    public @NotNull Map<String, GatewayClientInfo> getGatewayClientsByType(String type) {
        return null;
    }

    @Override
    public @Nullable GatewayClientInfo getClientById(String clientId) {
        return null;
    }
}
