package com.mayakplay.aclf.cloud.stereotype;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
@FunctionalInterface
public interface NuggetReceiveCallback {

    void nuggetReceived(Nugget nugget);

}