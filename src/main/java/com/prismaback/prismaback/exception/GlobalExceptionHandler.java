package com.prismaback.prismaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prismaback.prismaback.exception.lesson.LessonAlreadyExistsException;
import com.prismaback.prismaback.exception.lesson.LessonDeleteException;
import com.prismaback.prismaback.exception.lesson.LessonNotFoundException;
import com.prismaback.prismaback.exception.lesson.LessonUpdateException;
import com.prismaback.prismaback.exception.lesson.LessonValidationException;
import com.prismaback.prismaback.exception.question.QuestionAlreadyExistsException;
import com.prismaback.prismaback.exception.question.QuestionNotFoundException;
import com.prismaback.prismaback.exception.question.QuestionValidationException;
import com.prismaback.prismaback.exception.answer.AnswerAlreadyExistsException;
import com.prismaback.prismaback.exception.answer.AnswerNotFoundException;
import com.prismaback.prismaback.exception.answer.AnswerValidationException;
import com.prismaback.prismaback.exception.user.UserAlreadyExistsException;

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
                        "message", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", "Error intern del servidor",
                        "message", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LessonAlreadyExistsException.class)
    public ResponseEntity<?> handleLessonAlreadyExistsException(LessonAlreadyExistsException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", "Lliçó duplicada",
                        "message", ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LessonValidationException.class)
    public ResponseEntity<?> handleLessonValidationException(LessonValidationException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", "Error de validació",
                        "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LessonUpdateException.class)
    public ResponseEntity<?> handleLessonUpdateException(LessonUpdateException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", "Error d'actualització",
                        "message", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LessonDeleteException.class)
    public ResponseEntity<?> handleLessonDeleteException(LessonDeleteException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", "Error d'eliminació",
                        "message", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", "Usuari duplicat",
                        "message", ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(QuestionValidationException.class)
    public ResponseEntity<?> handleQuestionValidationException(QuestionValidationException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", "Error de validació de pregunta",
                        "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionAlreadyExistsException.class)
    public ResponseEntity<?> handleQuestionAlreadyExistsException(QuestionAlreadyExistsException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", "Pregunta duplicada",
                        "message", ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<?> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", "Pregunta no trobada",
                        "message", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnswerNotFoundException.class)
    public ResponseEntity<?> handleAnswerNotFoundException(AnswerNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", "Resposta no trobada",
                        "message", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnswerAlreadyExistsException.class)
    public ResponseEntity<?> handleAnswerAlreadyExistsException(AnswerAlreadyExistsException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", "Resposta duplicada",
                        "message", ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AnswerValidationException.class)
    public ResponseEntity<?> handleAnswerValidationException(AnswerValidationException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", "Error de validació de resposta",
                        "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
