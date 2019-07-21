package com.mayakplay.aclf.cloud;

import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayClient;
import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayServer;
import com.mayakplay.aclf.cloud.stereotype.GatewayClient;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public class Test {

    public static void main(String[] args) {

        GatewayServer gatewayServer = new NettyGatewayServer(8080);
        GatewayClient gatewayClient = new NettyGatewayClient("127.0.0.1", 8080);

    }

}