package com.mayakplay.aclf.cloud.stereotype;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
public interface Nugget {

    /**
     * Received message
     */
    @NotNull
    String getMessage();

    /**
     * An immutable map of parameters
     */
    @NotNull
    Map<String, String> getParameters();

    @Nullable
    default String getParameter(String key) {
        return getParameters().get(key);
    }

    default boolean hasParameter(String key) {
        return getParameters().containsKey(key);
    }

}