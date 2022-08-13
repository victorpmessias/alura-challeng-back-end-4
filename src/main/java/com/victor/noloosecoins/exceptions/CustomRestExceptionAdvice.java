package com.victor.noloosecoins.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.postgresql.util.PSQLException;

@RestControllerAdvice
public class CustomRestExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(MethodArgumentNotValidException ex) {
        String message = "Error trying fetch data";
        List<FieldErroDto> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> {
            FieldErroDto field = new FieldErroDto();
            field.setField(error.getField());
            field.setMessage(error.getDefaultMessage());
            return field;
        }).toList();
        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(errors);
        responseDto.setTimestamp(System.currentTimeMillis());
        return responseDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handler(HttpMessageNotReadableException ex) {
        String message = "Error trying to convert date";
        FieldErroDto error = new FieldErroDto();
        error.setMessage(ex.getMessage());
        error.setField("date");

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
        FieldErroDto error = new FieldErroDto("id", ex.getMessage());

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
        FieldErroDto error = new FieldErroDto("date", fieldMessage);


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
        FieldErroDto error = new FieldErroDto("date", fieldMessage);


        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setMessage(message);
        responseDto.setErrors(List.of(error));
        responseDto.setTimestamp(System.currentTimeMillis());

        return responseDto;
    }
}
