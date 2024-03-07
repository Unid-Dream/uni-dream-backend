package com.example.testSpringApp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import pwh.coreStarter.exception.UnifiedException;

public class Exceptions {
    public static UnifiedException PermissionDenied(Throwable cause) {
        return new UnifiedException("TA_E001", "Permission Denied", HttpStatus.FORBIDDEN.value(), cause);
    }

    public static UnifiedException InsufficientInput(Throwable cause, String... input) {
        return new UnifiedException("TA_E002", String.format("Permission Denied For Input: %s", StringUtils.join(input, ", ")), HttpStatus.BAD_REQUEST.value(), cause);
    }
}
