package com.prismaback.prismaback.exception.lesson;

public class LessonAlreadyExistsException extends RuntimeException {
    public LessonAlreadyExistsException(String title) {
        super("Ja existeix una lliçó amb el títol: " + title);
    }
}
