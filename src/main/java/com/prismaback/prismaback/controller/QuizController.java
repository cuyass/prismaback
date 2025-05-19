package com.prismaback.prismaback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prismaback.prismaback.DTO.QuizDTO;
import com.prismaback.prismaback.service.QuizService;
import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.DTO.EvaluationResultDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/{lessonId}")
    public ResponseEntity<QuizDTO> createQuiz(@PathVariable Long lessonId, @RequestBody QuizDTO quizDTO) {
        QuizDTO createdQuiz = quizService.createQuiz(lessonId, quizDTO);
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<QuizDTO> getQuizByLesson(@PathVariable Long lessonId) {
        return quizService.getQuizByLessonId(lessonId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{quizId}/evaluate")
    public ResponseEntity<List<EvaluationResultDTO>> evaluateQuiz(
            @PathVariable Long quizId,
            @RequestBody List<AnswerDTO> userAnswers) {
        List<EvaluationResultDTO> result = quizService.evaluateQuiz(quizId, userAnswers);
        return ResponseEntity.ok(result);
    }
}
