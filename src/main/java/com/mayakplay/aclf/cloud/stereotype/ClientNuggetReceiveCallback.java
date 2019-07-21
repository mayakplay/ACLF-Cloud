package com.mayakplay.aclf.cloud.stereotype;

import org.jetbrains.annotations.Nullable;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@FunctionalInterface
public interface ClientNuggetReceiveCallback {

    @Nullable
    void messageReceived(GatewayClientInfo serverInfo, Nugget nugget);

}