package com.mayakplay.aclf.cloud.stereotype;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public interface GatewayServer {

    /**
     * &lt;Server id, Server info&gt;
     * @return all connected servers
     */
    @NotNull
    Map<String, GatewayClientInfo> getClients();

    /**
     * &lt;Server id, Server info&gt;
     * @return connected servers by type
     */
    @NotNull
    Map<String, GatewayClientInfo> getGatewayClientsByType(String type);

    /**
     * @return server by id
     */
    @Nullable
    GatewayClientInfo getClientById(String clientId);

}