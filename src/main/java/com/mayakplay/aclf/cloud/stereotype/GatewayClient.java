package com.mayakplay.aclf.cloud.stereotype;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public interface GatewayClient {

    void sendNugget(String nuggetMessage, Map<String, String> parameters);

    void addReceiveCallback(NuggetReceiveCallback callback);

    boolean isRegistered();

}
