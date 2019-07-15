package com.mayakplay.aclf.cloud.listener;

import com.mayakplay.aclf.cloud.stereotype.ResponseNugget;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@FunctionalInterface
public interface ResponseNuggetReceiveListener {

    void onReceive(int connectionId, ResponseNugget responseNugget);

}
