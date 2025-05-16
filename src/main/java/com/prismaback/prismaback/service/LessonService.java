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

    public LessonDTO updateLesson(Long id, LessonDTO dto) {
        Lesson existing = lessonRepository.findById(id)
            .orElseThrow(() -> new LessonNotFoundException(id));

        validateLessonDTO(dto);

        if (lessonRepository.existsByTitle(dto.getTitle()) &&
            !existing.getTitle().equals(dto.getTitle())) {
            throw new LessonAlreadyExistsException(dto.getTitle());
        }

        try {
            existing.setTitle(dto.getTitle());
            existing.setMarkdownContent(dto.getMarkdownContent());
            Lesson updated = lessonRepository.save(existing);
            return toDTO(updated);
        } catch (Exception e) {
            throw new LessonUpdateException(id);
        }
    }

    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new LessonNotFoundException(id);
        }

        try {
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

    private void validateLessonDTO(LessonDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new LessonValidationException("El títol no pot estar buit.");
        }
    }
}
