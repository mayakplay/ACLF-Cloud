package com.mayakplay.aclf.cloud.stereotype;

import com.mayakplay.aclf.cloud.listener.NuggetReceiveListener;
import com.mayakplay.aclf.cloud.listener.ResponseNuggetReceiveListener;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
public interface GatewayServer {

    void start();

    boolean isStarted();

    void destroy();

    void addReceiveListener(NuggetReceiveListener listener);

    void addResponseNuggetListener(ResponseNuggetReceiveListener listener);

}