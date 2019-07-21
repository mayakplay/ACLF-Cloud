package com.mayakplay.aclf.cloud.stereotype;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
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
    Map<String, GatewayClientInfo> getClientsByType(String type);

    /**
     * @return server by id
     */
    @Nullable
    GatewayClientInfo getClientById(String clientId);

    /**
     * Sends nugget if client exists and registered
     */
    void sendToClient(@NotNull GatewayClientInfo clientInfo, @NotNull String message, @NotNull Map<String, String> params);

    /**
     * Sends nugget to all registered clients.
     */
    void sendToAll(@NotNull String message, @NotNull Map<String, String> params);

    /**
     * Shortcut for {@link #sendToClient(GatewayClientInfo, String, Map)}
     */
    default void sendToClient(@NotNull GatewayClientInfo clientInfo, @NotNull String message) {
        sendToClient(clientInfo, message, new HashMap<>());
    }

    /**
     * Shortcut for {@link #sendToAll(String, Map)}
     */
    default void sendToAll(@NotNull String message) {
        sendToAll(message, new HashMap<>());
    }


    void addReceiveCallback(ClientNuggetReceiveCallback callback);

    void addRegistrationHandler(ClientRegistrationHandler handler);
}