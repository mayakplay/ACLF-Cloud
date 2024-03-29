package com.mayakplay.aclf.cloud.stereotype;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public interface ClientRegistrationHandler {

    void onRegister(GatewayClientInfo clientInfo, Map<String, String> parameters);

    void onUnregister(GatewayClientInfo clientInfo);

}