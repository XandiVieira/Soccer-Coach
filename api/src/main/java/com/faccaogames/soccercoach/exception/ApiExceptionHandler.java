package com.faccaogames.soccercoach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            CustomNotFoundException.class
    })
    public ResponseEntity<Object> notFound(CustomNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {
            CustomAlreadyExistsException.class
    })
    public ResponseEntity<Object> alreadyExists(CustomAlreadyExistsException exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {
            CustomNotValidException.class
    })
    public ResponseEntity<Object> notValid(CustomNotValidException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }
}