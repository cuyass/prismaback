package com.prismaback.prismaback.exception.lesson;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("No s'ha trobat cap lliçó amb l'ID: " + id);
    }
}
