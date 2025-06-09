package com.dgg.project.controllers.exception;

import java.time.Instant;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dgg.project.services.exception.DatabaseException;
import com.dgg.project.services.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(NotFoundException e, WebRequest request) {
        String path = request.getDescription(false).replace("uri", "");

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException e,
            WebRequest request) {
        String path = request.getDescription(false).replace("uri", "");

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = "Data integrity Restriction";

        if (e.getCause() instanceof ConstraintViolationException) {
            message = "Constraint violation: " + e.getMostSpecificCause().getMessage();
        }

        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path);

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> handleDatabaseException(DatabaseException e, WebRequest request) {
        String path = request.getDescription(false).replace("uri", "");

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
