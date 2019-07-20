package com.mayakplay.aclf.cloud.infrastructure;

import com.google.common.collect.ImmutableMap;
import com.mayakplay.aclf.cloud.stereotype.Nugget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@Getter
@AllArgsConstructor
public class NuggetWrapper implements Nugget {

    @NotNull
    private final String message;

    @NotNull
    private final ImmutableMap<String, String> parameters;

}