package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.LessonDTO;
import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.repository.LessonRepository;
import com.prismaback.prismaback.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    
    private final LessonRepository lessonRepository;

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LessonDTO getLessonById(Long id) {
        return lessonRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new LessonNotFoundException(id)); 
    }

    public LessonDTO createLesson(LessonDTO dto) {
        Lesson lesson = Lesson.builder()
                .title(dto.getTitle())
                .markdownContent(dto.getMarkdownContent())
                .build();
        return toDTO(lessonRepository.save(lesson));
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Lesson existing = lessonRepository.findById(id).orElseThrow();
        existing.setTitle(updatedLesson.getTitle());
        existing.setMarkdownContent(updatedLesson.getMarkdownContent());
        return lessonRepository.save(existing);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    private LessonDTO toDTO(Lesson lesson) {
        return LessonDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .markdownContent(lesson.getMarkdownContent())
                .build();
    }
}
