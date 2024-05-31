package com.example.delivery_service.core.exception.handler;

import com.example.delivery_service.core.exception.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Tag(name="Исключения", description="Управление обработкой исключений")
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(final NotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleStatusException(final Exception exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}