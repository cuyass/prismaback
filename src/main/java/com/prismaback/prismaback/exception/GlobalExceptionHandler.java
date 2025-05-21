package com.prismaback.prismaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prismaback.prismaback.response.ApiResponse;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status)
            .body(ApiResponse.<T>builder()
                .message(message)
                .data(null)
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        return buildErrorResponse("Error intern del servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.lesson.LessonNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleLessonNotFoundException(Exception ex) {
        return buildErrorResponse("Lliçó no trobada: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.lesson.LessonAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleLessonAlreadyExistsException(Exception ex) {
        return buildErrorResponse("Lliçó duplicada: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.lesson.LessonValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleLessonValidationException(Exception ex) {
        return buildErrorResponse("Error de validació: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.lesson.LessonUpdateException.class)
    public ResponseEntity<ApiResponse<Void>> handleLessonUpdateException(Exception ex) {
        return buildErrorResponse("Error d'actualització: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.lesson.LessonDeleteException.class)
    public ResponseEntity<ApiResponse<Void>> handleLessonDeleteException(Exception ex) {
        return buildErrorResponse("Error d'eliminació: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.user.UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyExistsException(Exception ex) {
        return buildErrorResponse("Usuari duplicat: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.question.QuestionValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleQuestionValidationException(Exception ex) {
        return buildErrorResponse("Error de validació de pregunta: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.question.QuestionAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleQuestionAlreadyExistsException(Exception ex) {
        return buildErrorResponse("Pregunta duplicada: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.question.QuestionNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleQuestionNotFoundException(Exception ex) {
        return buildErrorResponse("Pregunta no trobada: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.answer.AnswerNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleAnswerNotFoundException(Exception ex) {
        return buildErrorResponse("Resposta no trobada: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.answer.AnswerAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAnswerAlreadyExistsException(Exception ex) {
        return buildErrorResponse("Resposta duplicada: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(com.prismaback.prismaback.exception.answer.AnswerValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAnswerValidationException(Exception ex) {
        return buildErrorResponse("Error de validació de resposta: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}