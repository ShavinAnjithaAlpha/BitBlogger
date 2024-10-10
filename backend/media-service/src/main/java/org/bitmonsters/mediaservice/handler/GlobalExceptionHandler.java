package org.bitmonsters.mediaservice.handler;

import org.bitmonsters.mediaservice.dto.ExceptionResponse;
import org.bitmonsters.mediaservice.exception.StorageException;
import org.bitmonsters.mediaservice.exception.StorageFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.time.LocalDateTime;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleStorageException(StorageException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleStorageFileNotFoundException(StorageFileNotFoundException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MalformedURLException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ExceptionResponse handleMalformedURLException(MalformedURLException exception) {
        return ExceptionResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
