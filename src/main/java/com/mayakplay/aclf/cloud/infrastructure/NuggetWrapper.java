package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.Nugget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@Getter
@AllArgsConstructor
@ToString
class NuggetWrapper implements Nugget {

    @NotNull
    private final String message;

    @NotNull
    private final Map<String, String> parameters;

}