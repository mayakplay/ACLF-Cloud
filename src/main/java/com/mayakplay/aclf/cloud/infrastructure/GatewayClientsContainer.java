package com.mayakplay.aclf.cloud.infrastructure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mayakplay.aclf.cloud.nugget.RegisterMessage;
import com.mayakplay.aclf.cloud.nugget.RegisterResponseMessage;
import com.mayakplay.aclf.cloud.stereotype.ClientNuggetReceiveCallback;
import com.mayakplay.aclf.cloud.stereotype.GatewayClientInfo;
import com.mayakplay.aclf.cloud.stereotype.Nugget;
import com.mayakplay.aclf.cloud.util.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@AllArgsConstructor
final class GatewayClientsContainer {

    private final Set<String> allowedIps;
    private final ClientNuggetReceiveCallback receiveCallback;

    /**
     * &lt;SocketAddress.toString(), Client info&gt;
     */
    private final Map<String, GatewayClientInfo> clientInfoMap = new HashMap<>();

    /**
     * Used to get context when a message is sent to a specific client.
     * Updated immediately after {@link #clientInfoMap}
     * <p>
     * &lt;Client id, Netty context&gt;
     */
    private final Map<String, ChannelHandlerContext> contextAssociationMap = new HashMap<>();

    /**
     * &lt;Client id, Client info&gt;
     */
    private final Map<String, GatewayClientInfo> clientsAssociationsMap = new HashMap<>();

    //region Getters
    @Nullable
    ChannelHandlerContext getContextByClientId(String clientId) {
        return contextAssociationMap.get(clientId);
    }

    @NotNull
    Collection<ChannelHandlerContext> getContainedContexts() {
        return ImmutableList.copyOf(contextAssociationMap.values());
    }

    @Nullable
    GatewayClientInfo getClientInfoById(String clientId) {
        return clientsAssociationsMap.get(clientId);
    }

    @NotNull
    Map<String, GatewayClientInfo> getClients() {
        return ImmutableMap.copyOf(clientsAssociationsMap);
    }

    @NotNull
    Map<String, GatewayClientInfo> getClientsByType(String type) {
        return ImmutableMap.copyOf(clientsAssociationsMap.entrySet().stream()
                .filter(entry -> entry.getValue().getClientType().equals(type))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b)));
    }
    //endregion

    //region Events
    boolean onConnect(SocketAddress socketAddress) {
        return allowedIps.contains(parseIp(socketAddress));
    }

    void onDisconnect(SocketAddress socketAddress) {
        final GatewayClientInfo remove = clientInfoMap.remove(socketAddress.toString());

        if (remove != null) {
            contextAssociationMap.remove(remove.getClientId());
            clientsAssociationsMap.remove(remove.getClientId());
        }
    }

    @Nullable
    Nugget onRead(ChannelHandlerContext ctx, SocketAddress socketAddress, Nugget nugget) {
        //region If client is registered
        final GatewayClientInfo gatewayClientInfo = clientInfoMap.get(socketAddress.toString());

        if (gatewayClientInfo != null) {
            receiveCallback.messageReceived(gatewayClientInfo, nugget);
            return null;
        }
        //endregion

        //region If client sent a register message
        final RegisterMessage registerMessage = JsonUtils.toObject(nugget.getMessage(), RegisterMessage.class);

        if (registerMessage != null) {
            final String generatedClientId = "id";

            final RegisteredClientInfo newClientInfo = new RegisteredClientInfo(generatedClientId, registerMessage.getClientType());
            clientInfoMap.put(socketAddress.toString(), newClientInfo);
            contextAssociationMap.put(newClientInfo.getClientId(), ctx);
            clientsAssociationsMap.put(newClientInfo.getClientId(), newClientInfo);
            System.out.println("Client registered: " + newClientInfo);

            final RegisterResponseMessage responseMessage = new RegisterResponseMessage(generatedClientId);

            return new NuggetWrapper(JsonUtils.toJson(responseMessage), new HashMap<>());
        }
        //endregion

        return null;
    }
    //endregion

    //region Util
    private static String parseIp(SocketAddress socketAddress) {
        String stringToParse = socketAddress.toString().replaceAll("[^0-9.:]", "");

        final StringBuilder ipStringBuilder = new StringBuilder();

        String[] split = stringToParse.split("[.:]");
        for (int i = 0; i < 4; i++) {
            String s = split[i];
            ipStringBuilder.append(s);
            if (i != 3) ipStringBuilder.append(".");
        }

        return ipStringBuilder.toString();
    }
    //endregion

}