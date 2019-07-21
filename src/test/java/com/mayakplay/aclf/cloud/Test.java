package com.mayakplay.aclf.cloud;

import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayClient;
import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayServer;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        GatewayServer gatewayServer = new NettyGatewayServer(8080);

        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);
        new NettyGatewayClient("127.0.0.1", 8080, "test").addReceiveCallback(System.out::println);

        Thread.sleep(10000);

        System.out.println(gatewayServer.getClients().size());

        gatewayServer.sendToAll("Hello world!");

    }

}