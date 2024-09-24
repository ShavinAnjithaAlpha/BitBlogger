package org.bitmonsters.tagservice.handler;

import org.bitmonsters.tagservice.exception.TagNotFoundException;
import org.bitmonsters.tagservice.exception.TooMuchTagsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(err -> {
                    var fieldName = ((FieldError) err).getField();
                    var errorMessage = err.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return new ErrorResponse(errors);
    }

    @ExceptionHandler(TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleTagNotFoundException(TagNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(TooMuchTagsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleTooMuchTagsException(TooMuchTagsException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

}
