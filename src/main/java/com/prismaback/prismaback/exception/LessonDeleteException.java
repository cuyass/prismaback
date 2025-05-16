package com.prismaback.prismaback.exception;

public class LessonDeleteException extends RuntimeException {
    public LessonDeleteException(Long id) {
        super("No s'ha pogut eliminar la lliçó amb l'ID " + id);
    }
}
