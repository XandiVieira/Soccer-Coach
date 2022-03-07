package com.faccaogames.soccercoach.exception;

public class CustomNotValidException extends CustomException {

    public CustomNotValidException(String message) {
        super(message);
    }

    public CustomNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}