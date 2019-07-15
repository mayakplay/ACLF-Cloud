package com.mayakplay.aclf.cloud.listener;

import com.mayakplay.aclf.cloud.stereotype.Nugget;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@FunctionalInterface
public interface NuggetReceiveListener {

    void onReceive(Nugget nugget);

}
