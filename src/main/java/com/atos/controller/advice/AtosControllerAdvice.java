package com.atos.controller.advice;

import com.atos.domain.exception.BadRequestException;
import com.atos.domain.exception.UserNotFoundException;
import com.atos.domain.response.ExceptionResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class AtosControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse onUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ExceptionResponse(
                INTERNAL_SERVER_ERROR,
                "USER_NOT_FOUND",
                userNotFoundException.getMessage()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse onBadRequestException(BadRequestException badRequestException) {
        return new ExceptionResponse(
                INTERNAL_SERVER_ERROR,
                "BAD_DATA_INPUT",
                badRequestException.getMessage()
        );
    }
}
