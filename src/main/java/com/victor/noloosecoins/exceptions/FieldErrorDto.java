package com.victor.noloosecoins.exceptions;


import com.fasterxml.jackson.annotation.JsonInclude;
public class FieldErrorDto {

    private String field;
    private String error;

    public FieldErrorDto() {
    }

    public FieldErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
