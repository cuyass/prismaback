package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.LessonDTO;
import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.repository.LessonRepository;
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
                .orElse(null); 
    }

    public LessonDTO createLesson(LessonDTO dto) {
        Lesson lesson = Lesson.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return toDTO(lessonRepository.save(lesson));
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    private LessonDTO toDTO(Lesson lesson) {
        return LessonDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .build();
    }
}
