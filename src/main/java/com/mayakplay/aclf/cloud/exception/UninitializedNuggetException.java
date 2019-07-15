package com.mayakplay.aclf.cloud.exception;

import lombok.AllArgsConstructor;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 15.07.2019.
 */
@AllArgsConstructor
public final class UninitializedNuggetException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Nugget is not initialized by proxy!";
    }

}
