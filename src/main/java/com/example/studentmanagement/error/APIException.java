package com.example.studentmanagement.error;

import org.springframework.http.HttpStatus;

public class APIException {
    private HttpStatus status;
    private String message;
    public APIException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
