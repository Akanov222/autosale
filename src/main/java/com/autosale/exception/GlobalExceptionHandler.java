package com.autosale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCarTypeException.class)
    public ProblemDetail handleInvalidCarType(InvalidCarTypeException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Invalid Car Type");
        problemDetail.setProperty("errorCode", "INVALID_CAR_TYPE");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
