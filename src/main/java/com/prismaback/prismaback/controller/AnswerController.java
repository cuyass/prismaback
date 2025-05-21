package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.service.AnswerService;
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
    public ResponseEntity<List<AnswerDTO>> getAnswersByQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestionId(questionId));
    }

    @PostMapping("/question/{questionId}")
    public ResponseEntity<AnswerDTO> createAnswer(
            @PathVariable Long questionId,
            @RequestBody AnswerDTO dto) {
        return ResponseEntity.ok(answerService.createAnswer(questionId, dto));
    }
}
