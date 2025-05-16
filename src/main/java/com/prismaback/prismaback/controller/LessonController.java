package com.prismaback.prismaback.controller;

import com.prismaback.prismaback.DTO.LessonDTO;
import com.prismaback.prismaback.response.ApiResponse;
import com.prismaback.prismaback.service.LessonService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LessonDTO>>> getAllLessons() {
        List<LessonDTO> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(ApiResponse.<List<LessonDTO>>builder()
            .message("Lliçons carregades correctament.")
            .data(lessons)
            .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LessonDTO>> getLessonById(@PathVariable Long id) {
        LessonDTO lesson = lessonService.getLessonById(id);
        return ResponseEntity.ok(ApiResponse.<LessonDTO>builder()
            .message("Lliçó trobada.")
            .data(lesson)
            .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LessonDTO>> createLesson(@RequestBody LessonDTO lessonDTO) {
        LessonDTO created = lessonService.createLesson(lessonDTO);
        return ResponseEntity.status(201).body(ApiResponse.<LessonDTO>builder()
            .message("Lliçó creada correctament.")
            .data(created)
            .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LessonDTO>> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        LessonDTO updatedLesson = lessonService.updateLesson(id, lessonDTO);
        return ResponseEntity.ok(ApiResponse.<LessonDTO>builder()
            .message("Lliçó actualitzada correctament.")
            .data(updatedLesson)
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
            .message("Lliçó eliminada correctament.")
            .data(null)
            .build());
    }
}
