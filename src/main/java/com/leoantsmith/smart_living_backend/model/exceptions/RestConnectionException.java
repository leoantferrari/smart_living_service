package com.leoantsmith.smart_living_backend.model.exceptions;

public class RestConnectionException extends RuntimeException {

    public RestConnectionException(String message) {
        super(message);
    }
}
