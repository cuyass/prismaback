package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.service.QuestionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByLessonId(@PathVariable Long lessonId) {
        return ResponseEntity.ok(questionService.getQuestionsByLessonId(lessonId));
    }

    @PostMapping("/lesson/{lessonId}")
    public ResponseEntity<QuestionDTO> createQuestion(@PathVariable Long lessonId, @RequestBody QuestionDTO dto) {
        QuestionDTO created = questionService.createQuestion(lessonId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.updateQuestion(questionId, dto));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
