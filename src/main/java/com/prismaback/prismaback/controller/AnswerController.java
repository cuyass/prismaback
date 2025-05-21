package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.service.AnswerService;
import com.prismaback.prismaback.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AnswerController {
    
    private final AnswerService answerService;

    @GetMapping("/question/{questionId}")
    public ResponseEntity<ApiResponse<List<AnswerDTO>>> getAnswersByQuestion(@PathVariable Long questionId) {
        List<AnswerDTO> answers = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(ApiResponse.<List<AnswerDTO>>builder()
            .message("Respostes carregades correctament.")
            .data(answers)
            .build());
    }

    @PostMapping("/question/{questionId}")
    public ResponseEntity<ApiResponse<AnswerDTO>> createAnswer(
            @PathVariable Long questionId,
            @RequestBody AnswerDTO dto) {
        AnswerDTO created = answerService.createAnswer(questionId, dto);
        return ResponseEntity.status(201).body(ApiResponse.<AnswerDTO>builder()
            .message("Resposta creada correctament.")
            .data(created)
            .build());
    }
}
