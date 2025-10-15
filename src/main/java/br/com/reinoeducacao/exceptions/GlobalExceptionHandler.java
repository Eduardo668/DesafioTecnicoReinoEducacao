package br.com.reinoeducacao.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteException.class)
    public ResponseEntity<ApiError>  handleClienteException(
            ClienteException clienteException, HttpServletRequest request
    ) {
        var body = new ApiError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                clienteException.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ApiError>  handleClienteNotFoundException(
            ClienteNotFoundException exception, HttpServletRequest request
    ) {
        var body = new ApiError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError>  handleConstraintViolationException(
            ConstraintViolationException exception, HttpServletRequest request
    ) {
        var body = new ApiError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

    }


    @ExceptionHandler(InsufficientMilesException.class)
    public ResponseEntity<ApiError>  handleInsufficientMilesException(
            InsufficientMilesException exception, HttpServletRequest request
    ) {
        var body = new ApiError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }


}
