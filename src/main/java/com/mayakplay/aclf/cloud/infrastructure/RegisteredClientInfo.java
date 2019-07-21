package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayClientInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 20.07.2019.
 */
@Getter
@AllArgsConstructor
@ToString
final class RegisteredClientInfo implements GatewayClientInfo {

    private final String clientId;
    private final String clientType;

}