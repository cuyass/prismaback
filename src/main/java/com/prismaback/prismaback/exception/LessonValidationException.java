package com.prismaback.prismaback.exception;

public class LessonValidationException extends RuntimeException {
    public LessonValidationException(String message) {
        super("Error de validació: " + message);
    }
}
