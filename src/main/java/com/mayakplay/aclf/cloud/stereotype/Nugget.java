package com.mayakplay.aclf.cloud.stereotype;

import com.mayakplay.aclf.cloud.exception.UninitializedNuggetException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
public interface Nugget {

    @NotNull
    default long getConnectionId() {
        throw new UninitializedNuggetException();
    }

    @NotNull
    default Type getNuggetType() {
        throw new UninitializedNuggetException();
    }

}