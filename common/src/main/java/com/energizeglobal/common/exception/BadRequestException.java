package com.energizeglobal.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by test on 7/26/2016.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends HttpException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
