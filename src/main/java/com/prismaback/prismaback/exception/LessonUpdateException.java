package com.prismaback.prismaback.exception;

public class LessonUpdateException extends RuntimeException {
    public LessonUpdateException(Long id) {
        super("No s'ha pogut actualitzar la lliçó amb l'ID " + id);
    }
}
