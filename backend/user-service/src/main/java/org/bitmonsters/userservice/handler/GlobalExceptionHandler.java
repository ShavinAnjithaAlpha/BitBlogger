package org.bitmonsters.userservice.handler;

import org.bitmonsters.userservice.exception.UserAlreadyRegisteredException;
import org.bitmonsters.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(err -> {
                    var fieldName = ((FieldError) err).getField();
                    var errorMessage = err.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionResponse> handleUSerAlreadyRegisteredException(UserAlreadyRegisteredException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(exception.getMessage()));
    }

}
