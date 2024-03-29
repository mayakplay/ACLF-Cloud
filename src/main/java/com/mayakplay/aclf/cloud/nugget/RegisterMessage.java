package com.mayakplay.aclf.cloud.nugget;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
@Getter
@AllArgsConstructor
public final class RegisterMessage {

    private final String clientType;

    private Map<String, String> parameters;

}