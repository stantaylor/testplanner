package com.aphids.testplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException() {

    }
}
