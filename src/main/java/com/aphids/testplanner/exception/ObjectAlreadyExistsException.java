package com.aphids.testplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ObjectAlreadyExistsException extends BusinessException {

    String message;

    public ObjectAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public ObjectAlreadyExistsException() {

    }
}
