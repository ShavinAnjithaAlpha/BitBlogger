package org.bitmonsters.authserver.handler;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.authserver.exception.AuthException;
import org.bitmonsters.authserver.exception.UserAlreadyExistsException;
import org.bitmonsters.authserver.exception.UserNotFoundException;
import org.bitmonsters.authserver.repository.AuditLogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final AuditLogRepository auditLogRepository;

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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handleAuthException(AuthException exception) {
        // create an audit log of what happened

        return ResponseEntity
                .status(exception.getStatus())
                .body(ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .status(exception.getStatus())
                        .timestamp(LocalDateTime.now())
                        .description(exception.getDescription())
                        .build());
    }

    @ExceptionHandler(AccountLockedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleAccountLockedException(AccountLockedException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .timestamp(LocalDateTime.now())
                .build();
    }



}
