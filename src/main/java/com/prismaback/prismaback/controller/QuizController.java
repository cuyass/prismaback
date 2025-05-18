package com.prismaback.prismaback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prismaback.prismaback.model.Quiz;
import com.prismaback.prismaback.service.QuizService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.prismaback.prismaback.model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/{lessonId}")
    public ResponseEntity<Quiz> createQuiz(
            @PathVariable Long lessonId,
            @RequestBody List<Question> questions) {
        Quiz quiz = quizService.createQuiz(lessonId, questions);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<Quiz> getQuizByLesson(@PathVariable Long lessonId) {
        return quizService.getQuizByLessonId(lessonId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
