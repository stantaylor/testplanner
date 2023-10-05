package com.aphids.testplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    String message;

    public ObjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ObjectNotFoundException() {

    }
}
