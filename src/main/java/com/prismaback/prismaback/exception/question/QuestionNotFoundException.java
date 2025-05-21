package com.prismaback.prismaback.exception.question;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Long id) {
        super("No s'ha trobat cap pregunta amb l'ID: " + id);
    }
}