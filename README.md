# ACLF-Cloud

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/977a0481d6ff437db4aef8025dd66931)](https://app.codacy.com/app/mayakplay/ACLF-Cloud?utm_source=github.com&utm_medium=referral&utm_content=mayakplay/ACLF-Cloud&utm_campaign=Badge_Grade_Dashboard)

<img src="https://pp.userapi.com/c849432/v849432835/1da138/tKVV6IG4r8I.jpg" align="center" height="250">


## Usage example

```java
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
     * When a client receives a message, the method prints it.ччччч
     *
     * @param nugget received nugget
     */
    private static void onMessage(Nugget nugget) {
        System.out.println("Server sent: " + nugget);
    }

}
```
