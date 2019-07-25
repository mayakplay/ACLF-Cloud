package com.mayakplay.aclf.cloud.stereotype;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 25.07.2019.
 */
@FunctionalInterface
public interface RegistrationCallback {

    void onRegister(GatewayInfo gatewayInfo, Map<String, String> parameters);

}
