package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@AllArgsConstructor
public final class GatewayServerThread extends Thread implements GatewayServer {

    private final int selfNuggetPort;

    @Override
    @SneakyThrows
    public void run() {
        startServer();
    }

    private void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(selfNuggetPort); Socket socket = serverSocket.accept()) {
            while (!getState().equals(State.TERMINATED)) {
                final InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                final BufferedReader reader = new BufferedReader(inputStreamReader);
                final String caughtContext = reader.lines().collect(Collectors.joining());

//                processingThread.toProcessingQueue(socket.getInetAddress(), caughtContext);
            }
        }
    }

}