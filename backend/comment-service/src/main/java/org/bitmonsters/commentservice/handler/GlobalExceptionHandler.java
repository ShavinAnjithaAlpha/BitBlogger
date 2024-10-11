package org.bitmonsters.commentservice.handler;

import org.bitmonsters.commentservice.exception.CommentException;
import org.bitmonsters.commentservice.exception.CommentNotFoundException;
import org.bitmonsters.commentservice.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(err -> {
                    String fieldName;
                    try {
                        fieldName = ((FieldError) err).getField();
                    } catch (ClassCastException exp) {
                        fieldName = err.getObjectName();
                    }
                    var errorMessage = err.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return new ErrorResponse(errors);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlePostNotFoundException(PostNotFoundException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleCommentNotFoundException(CommentNotFoundException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ExceptionResponse> handleCommentException(CommentException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .status(exception.getStatus())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
