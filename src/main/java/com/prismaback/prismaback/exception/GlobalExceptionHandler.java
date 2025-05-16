package com.prismaback.prismaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LessonNotFoundException.class)
    public ResponseEntity<?> handleLessonNotFoundException(LessonNotFoundException ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Lliçó no trobada",
                "message", ex.getMessage()
            ),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Error intern del servidor",
                "message", ex.getMessage()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(LessonAlreadyExistsException.class)
    public ResponseEntity<?> handleLessonAlreadyExistsException(LessonAlreadyExistsException ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Lliçó duplicada",
                "message", ex.getMessage()
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(LessonValidationException.class)
    public ResponseEntity<?> handleLessonValidationException(LessonValidationException ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Error de validació",
                "message", ex.getMessage()
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(LessonUpdateException.class)
    public ResponseEntity<?> handleLessonUpdateException(LessonUpdateException ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Error d'actualització",
                "message", ex.getMessage()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(LessonDeleteException.class)
    public ResponseEntity<?> handleLessonDeleteException(LessonDeleteException ex) {
        return new ResponseEntity<>(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Error d'eliminació",
                "message", ex.getMessage()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
