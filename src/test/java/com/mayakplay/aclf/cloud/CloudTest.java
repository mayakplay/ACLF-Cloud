package com.mayakplay.aclf.cloud;


import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import com.mayakplay.aclf.cloud.util.builder.CloudBuilder;
import org.junit.After;
import org.junit.Test;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
public class CloudTest {

    @After
    public void gatewayClientTest() {



    }

    @Test
    public void gatewayServerTest() {

        final GatewayServer gatewayServer = CloudBuilder.newGatewayBuilder()
                .setNuggetPort(1250)
                .build();

        gatewayServer.addReceiveListener(System.out::println);

        gatewayServer.start();

    }

}
