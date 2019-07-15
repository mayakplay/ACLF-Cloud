package com.mayakplay.aclf.cloud.util.builder;

import com.google.common.base.Preconditions;
import com.mayakplay.aclf.cloud.infrastructure.GatewayServerThread;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Set;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@Setter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GatewayBuilder {

    private int nuggetPort;
    private Set<String> allowedHosts;

    public GatewayServer build() {
        Preconditions.checkArgument(nuggetPort > 0);
        Objects.requireNonNull(allowedHosts);

        return new GatewayServerThread(nuggetPort, allowedHosts);
    }

}
