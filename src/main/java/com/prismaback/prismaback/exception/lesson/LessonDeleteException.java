package com.prismaback.prismaback.exception.lesson;

public class LessonDeleteException extends RuntimeException {
    public LessonDeleteException(Long id) {
        super("No s'ha pogut eliminar la lliçó amb l'ID " + id);
    }
}
