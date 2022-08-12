package com.victor.noloosecoins.exceptions;


public class FieldErroDto {

    private String field;
    private String message;

    public FieldErroDto() {
    }

    public FieldErroDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
