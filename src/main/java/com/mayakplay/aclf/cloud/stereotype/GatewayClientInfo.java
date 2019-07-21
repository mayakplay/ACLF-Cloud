package com.mayakplay.aclf.cloud.stereotype;

import com.mayakplay.aclf.cloud.util.Pair;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public interface GatewayClientInfo {

    String getClientId();

    String getClientType();

    void sendNugget(String message, Pair<String, String>... params);

}