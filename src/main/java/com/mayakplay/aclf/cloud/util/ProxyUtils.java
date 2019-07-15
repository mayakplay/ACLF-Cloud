package com.mayakplay.aclf.cloud.util;

import com.mayakplay.aclf.cloud.stereotype.Nugget;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Proxy;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProxyUtils {

    public static Nugget getProxiedNugget(Nugget nugget, long connectionId) {
        final NuggetInvocationHandler nuggetInvocationHandler = new NuggetInvocationHandler(nugget, connectionId);

        return (Nugget) Proxy.newProxyInstance(
                nugget.getClass().getClassLoader(),
                new Class[]{Nugget.class},
                nuggetInvocationHandler
        );
    }

}