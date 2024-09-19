package org.bitmonsters.topicservice.handler;

import org.bitmonsters.topicservice.exception.TopicAlreadyExistsException;
import org.bitmonsters.topicservice.exception.TopicNotExistsException;
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

    @ExceptionHandler(TopicAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleTopicAlreadyExistsException(TopicAlreadyExistsException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
    }

    @ExceptionHandler(TopicNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleTopicNotExistsException(TopicNotExistsException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
    }

}
