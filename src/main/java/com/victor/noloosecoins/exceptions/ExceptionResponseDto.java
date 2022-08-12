package com.victor.noloosecoins.exceptions;

import java.util.List;

public class ExceptionResponseDto {

    private String message;
    private List<FieldErroDto> errors;
    private Long timestamp;

    public ExceptionResponseDto() {
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErroDto> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErroDto> errors) {
        this.errors = errors;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
