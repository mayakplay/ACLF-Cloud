package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayClientInfo;
import com.mayakplay.aclf.cloud.util.Pair;
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
public class RegisteredClientInfo implements GatewayClientInfo {

    private final String clientId;
    private final String clientType;

    @Override
    public void sendNugget(String message, Pair<String, String>... params) {

    }
}
