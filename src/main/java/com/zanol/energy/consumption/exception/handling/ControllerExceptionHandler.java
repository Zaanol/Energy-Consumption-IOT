package com.zanol.energy.consumption.exception.handling;

import com.zanol.energy.consumption.exception.model.ErrorMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ErrorMessage> handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>(
            new ErrorMessage(ex.getCause().getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorMessage> handleGenericError(Exception ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}