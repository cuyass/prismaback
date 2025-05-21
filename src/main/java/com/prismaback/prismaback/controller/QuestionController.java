package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.service.QuestionService;
import com.prismaback.prismaback.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<QuestionDTO>>> getQuestionsByLessonId(@PathVariable Long lessonId) {
        List<QuestionDTO> questions = questionService.getQuestionsByLessonId(lessonId);
        return ResponseEntity.ok(ApiResponse.<List<QuestionDTO>>builder()
            .message("Preguntes carregades correctament.")
            .data(questions)
            .build());
    }

    @PostMapping("/lesson/{lessonId}")
    public ResponseEntity<ApiResponse<QuestionDTO>> createQuestion(@PathVariable Long lessonId, @RequestBody QuestionDTO dto) {
        QuestionDTO created = questionService.createQuestion(lessonId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<QuestionDTO>builder()
            .message("Pregunta creada correctament.")
            .data(created)
            .build());
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<ApiResponse<QuestionDTO>> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDTO dto) {
        QuestionDTO updated = questionService.updateQuestion(questionId, dto);
        return ResponseEntity.ok(ApiResponse.<QuestionDTO>builder()
            .message("Pregunta actualitzada correctament.")
            .data(updated)
            .build());
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
            .message("Pregunta eliminada correctament.")
            .data(null)
            .build());
    }
}
