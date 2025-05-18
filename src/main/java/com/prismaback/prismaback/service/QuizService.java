package com.prismaback.prismaback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.model.Quiz;
import com.prismaback.prismaback.repository.LessonRepository;
import com.prismaback.prismaback.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;

    public Quiz createQuiz(Long lessonId, List<Question> questions) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lliçó no trobada"));

        Quiz quiz = Quiz.builder()
                .lesson(lesson)
                .questions(new ArrayList<>())
                .build();

        for (Question q : questions) {
            q.setQuiz(quiz);
            quiz.getQuestions().add(q);
        }

        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizByLessonId(Long lessonId) {
        return quizRepository.findByLessonId(lessonId);
    }
}

