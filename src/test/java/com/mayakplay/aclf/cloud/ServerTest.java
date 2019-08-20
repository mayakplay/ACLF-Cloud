package com.mayakplay.aclf.cloud;

import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayServer;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;

import java.util.HashMap;

/**
 * @author mayakplay
 * @since 19.08.2019.
 */
public class ServerTest {
    private static final int PORT = 8080;

    public static void main(String[] args) {

        final GatewayServer gatewayServer = new NettyGatewayServer(PORT, new HashMap<>());

    }

}
