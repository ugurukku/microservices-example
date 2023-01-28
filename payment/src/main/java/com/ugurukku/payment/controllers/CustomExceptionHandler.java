package com.ugurukku.payment.controllers;

import com.ugurukku.payment.exceptions.ExceptionResponse;
import com.ugurukku.payment.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ugurukku.payment.models.constant.ExceptionConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleInternal(Exception exception){
        log.error("Exception: ", exception);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE,UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleNotFound(NotFoundException exception){
        log.error("Exception: "+ exception);
        return new ExceptionResponse(exception.getCode(), exception.getMessage());
    }

}
