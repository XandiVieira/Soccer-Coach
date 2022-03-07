package com.faccaogames.soccercoach.exception;

public class CustomAlreadyExistsException extends CustomException {

    public CustomAlreadyExistsException(String message) {
        super(message);
    }

    public CustomAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
