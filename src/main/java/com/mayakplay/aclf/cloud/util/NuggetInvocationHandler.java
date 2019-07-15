package com.mayakplay.aclf.cloud.util;

import com.mayakplay.aclf.cloud.stereotype.Nugget;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
public final class NuggetInvocationHandler implements InvocationHandler {

    private final Nugget originalNugget;
    private final long connectionId;
    private final Class<?> originalNuggetType;

    public NuggetInvocationHandler(Nugget originalNugget, long connectionId) {
        this.originalNugget = originalNugget;
        this.connectionId = connectionId;
        this.originalNuggetType = originalNugget.getClass();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getConnectionId")) {
            return connectionId;
        }

        if (method.getName().equals("getNuggetType")) {
            return originalNuggetType;
        }

        return method.invoke(originalNugget, args);
    }
}
