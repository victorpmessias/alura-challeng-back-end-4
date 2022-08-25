package com.victor.noloosecoins.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
public class ExceptionResponseDto {

    private String message;
    private List<FieldErrorDto> errors;
    private Long timestamp;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorDto> errors) {
        this.errors = errors;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
