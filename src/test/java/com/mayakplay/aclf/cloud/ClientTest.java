package com.mayakplay.aclf.cloud;

import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayClient;

import java.util.HashMap;

/**
 * @author mayakplay
 * @since 19.08.2019.
 */
public class ClientTest {

    private static final int PORT = 8080;

    public static void main(String[] args) throws InterruptedException {

        final NettyGatewayClient gatewayClient = new NettyGatewayClient("127.0.0.1", PORT, "test", new HashMap<>());

        while (true) {
            Thread.sleep(1000);
            System.out.println(gatewayClient.isRegistered());
        }
    }

}
