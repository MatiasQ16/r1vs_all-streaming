package com.tests.r1vs_allstreaming.Utils;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;


    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
