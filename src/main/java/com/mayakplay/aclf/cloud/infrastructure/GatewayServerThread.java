package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.listener.NuggetReceiveListener;
import com.mayakplay.aclf.cloud.listener.ResponseNuggetReceiveListener;
import com.mayakplay.aclf.cloud.stereotype.GatewayServer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@RequiredArgsConstructor
public final class GatewayServerThread extends Thread implements GatewayServer {

    private boolean started = false;

    private final int selfNuggetPort;

    @NotNull
    private final Set<String> allowedHosts;

    @NotNull
    private ArrayList<NuggetReceiveListener> nuggetReceiveListeners = new ArrayList<>();

    @NotNull
    private ArrayList<ResponseNuggetReceiveListener> responseNuggetReceiveListeners = new ArrayList<>();

    @Override
    @SneakyThrows
    public void run() {
        started = true;
        startServer();
    }

    private void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(selfNuggetPort); Socket socket = serverSocket.accept()) {
            while (!getState().equals(State.TERMINATED)) {
                final InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                final BufferedReader reader = new BufferedReader(inputStreamReader);
                final String caughtContext = reader.lines().collect(Collectors.joining());

                System.out.println(caughtContext);

//                processingThread.toProcessingQueue(socket.getInetAddress(), caughtContext);
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void destroy() {
        started = true;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public void addReceiveListener(NuggetReceiveListener listener) {
        nuggetReceiveListeners.add(listener);
    }

    @Override
    public void addResponseNuggetListener(ResponseNuggetReceiveListener listener) {
        responseNuggetReceiveListeners.add(listener);
    }
}