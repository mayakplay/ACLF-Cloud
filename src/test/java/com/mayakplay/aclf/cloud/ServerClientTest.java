package com.mayakplay.aclf.cloud;

import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayClient;
import com.mayakplay.aclf.cloud.infrastructure.NettyGatewayServer;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import com.mayakplay.aclf.cloud.stereotype.Nugget;

/**
 * Usage example
 *
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public class ServerClientTest {

    /**
     * Hosted port
     */
    private static final int PORT = 8080;

    /**
     * How many test clients will be created
     */
    private static final int TEST_CLIENTS_COUNT = 3;

    /**
     * Starts client and server
     */
    public static void main(String[] args) throws InterruptedException {
        //Creating and starting a server
        final GatewayServer gatewayServer = new NettyGatewayServer(PORT);

        //Looping clients creation
        for (int i = 0; i < TEST_CLIENTS_COUNT; i++) {
            //Creating and running a client
            final NettyGatewayClient gatewayClient = new NettyGatewayClient("127.0.0.1", PORT, "test");

            //Adding a listener for incoming nuggets
            gatewayClient.addReceiveCallback(ServerClientTest::onMessage);
        }

        //Delay emulation
        Thread.sleep(3000);

        //Send nugget to all clients
        gatewayServer.sendToAll("Hello world!");
    }

    /**
     * Callback refers to this method.
     * When a client receives a message, the method prints it
     *
     * @param nugget received nugget
     */
    private static void onMessage(Nugget nugget) {
        System.out.println("Server sent: " + nugget);
    }

}