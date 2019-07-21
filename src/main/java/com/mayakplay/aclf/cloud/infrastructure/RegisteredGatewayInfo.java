package com.mayakplay.aclf.cloud.infrastructure;

import com.mayakplay.aclf.cloud.stereotype.GatewayInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
@Getter
@AllArgsConstructor
public class RegisteredGatewayInfo implements GatewayInfo {

    private String assignedId;

}
