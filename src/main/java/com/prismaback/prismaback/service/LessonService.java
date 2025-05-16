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

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new LessonValidationException("El títol no pot estar buit.");
        }
        if (lessonRepository.existsByTitle(dto.getTitle())) {
            throw new LessonAlreadyExistsException(dto.getTitle());
        }
    
        Lesson lesson = Lesson.builder()
                .title(dto.getTitle())
                .markdownContent(dto.getMarkdownContent())
                .build();
    
        return toDTO(lessonRepository.save(lesson));
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Lesson existing = lessonRepository.findById(id)
        .orElseThrow(() -> new LessonNotFoundException(id));

        if (updatedLesson.getTitle() == null || updatedLesson.getTitle().isBlank()) {
            throw new LessonValidationException("El títol no pot estar buit.");
        }

        if (lessonRepository.existsByTitle(updatedLesson.getTitle()) &&
            !existing.getTitle().equals(updatedLesson.getTitle())) {
            throw new LessonAlreadyExistsException(updatedLesson.getTitle());
        }

        try {
        existing.setTitle(updatedLesson.getTitle());
        existing.setMarkdownContent(updatedLesson.getMarkdownContent());
        return lessonRepository.save(existing);
        } catch (Exception e) {
            throw new LessonUpdateException(id);
        }
    }

    public void deleteLesson(Long id) {

        try{
            lessonRepository.deleteById(id);
        } catch (Exception e) {
            throw new LessonDeleteException(id);
        }
    }

    private LessonDTO toDTO(Lesson lesson) {
        return LessonDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .markdownContent(lesson.getMarkdownContent())
                .build();
    }
}
