package com.atos.domain.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime timestamp;

    private int statusCode;

    private String error;

    private String message;

    public ExceptionResponse(HttpStatus httpStatus, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = httpStatus.value();
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
