package com.victor.noloosecoins.exceptions;

import org.postgresql.util.PSQLException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class CustomRestExceptionAdvice {
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(HttpMediaTypeNotSupportedException ex) {
        String message = "invalid media type";
        FieldErrorDto error = new FieldErrorDto();
        error.setError("payload media type is not valid in this operation");

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(MethodArgumentNotValidException ex) {
        String message = "Error trying fetch data";
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> {
            FieldErrorDto field = new FieldErrorDto();
            field.setField(error.getField());
            field.setError(error.getDefaultMessage());
            return field;
        }).toList();
        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(errors);
        responseDto.setTimestamp(System.currentTimeMillis());
        return responseDto;
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(DateTimeParseException ex) {
        String message = "Error trying to convert date";
        FieldErrorDto error = new FieldErrorDto();
        error.setError("date has not in correct format, must be dd/MM/yyyy");
        error.setField("date");

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(HttpMessageNotReadableException ex) {
        if (ex.getCause().toString().contains("JsonParseException")) {
            return badJsonFormatHandler(ex);
        }
        if (ex.getCause().toString().contains("DateTimeParseException")) {
            return badDateFormatHandler(ex);
        }
        return null;
    }

    private ExceptionResponseDto badDateFormatHandler(HttpMessageNotReadableException ex) {
        String message = "Error trying to convert date";
        FieldErrorDto error = new FieldErrorDto();
        error.setError("date has not in correct format, must be dd/MM/yyyy");


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;

    }

    private ExceptionResponseDto badJsonFormatHandler(HttpMessageNotReadableException ex) {
        String message = "Error fetching json";
        FieldErrorDto error = new FieldErrorDto();
        error.setError("json is in a invalid format");
        error.setField("error");

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;

    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handler(EntityNotFoundException ex) {
        String message = "Resource Not Found";
        FieldErrorDto error = new FieldErrorDto("id", ex.getMessage());

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(SQLIntegrityConstraintViolationException ex) {

        String fieldMessage =
                ex.getMessage().contains(".description")
                        ? "There is already an expense registry with given description"
                        : "Error";


        String message = "Error trying fetch data";
        FieldErrorDto error = new FieldErrorDto(null, fieldMessage);


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }


    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(PSQLException ex) {

        String fieldMessage =
                ex.getMessage().contains("_description")
                        ? "There is already an expense registry with given description"
                        : "Error";


        String message = "Error trying fetch data";
        FieldErrorDto error = new FieldErrorDto(null, fieldMessage);


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }


    @ExceptionHandler(InvalidDateArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(InvalidDateArgumentException ex) {


        String message = "Error during parse";
        FieldErrorDto error = new FieldErrorDto("parameters", ex.getMessage());


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(InvalidEmailException ex) {


        String message = "Error";
        FieldErrorDto error = new FieldErrorDto("parameters", ex.getMessage());


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }


    @ExceptionHandler(AlreadyRegisteredDescription.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(AlreadyRegisteredDescription ex) {

        String fieldMessage =
                "There is already an expense registry with given description on month";
        FieldErrorDto error = new FieldErrorDto("description", fieldMessage);

        String message = "Error trying fetch data";
        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(PropertyReferenceException ex) {

        String message =
                "Invalid field name";

        FieldErrorDto error = new FieldErrorDto("sort", ex.getMessage());

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);

        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }

}
