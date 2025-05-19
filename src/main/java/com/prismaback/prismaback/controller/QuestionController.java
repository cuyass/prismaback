package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    private final QuestionService questionService;

    /* @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    } */

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByLessonId(@PathVariable Long lessonId) {
        return ResponseEntity.ok(questionService.getQuestionsByLessonId(lessonId));
    }

    /* @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    } */

    @PostMapping("/lesson/{lessonId}")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestParam Long id, @RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.createQuestion(id, dto));
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO dto) {
        return ResponseEntity.ok(questionService.updateQuestion(id, dto));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
