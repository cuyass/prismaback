package com.prismaback.prismaback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prismaback.prismaback.DTO.QuizDTO;
import com.prismaback.prismaback.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/{lessonId}")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO)
             {
                QuizDTO createdQuiz = quizService.createQuiz(quizDTO);
                return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<QuizDTO> getQuizByLesson(@PathVariable Long lessonId) {
        return quizService.getQuizByLessonId(lessonId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
