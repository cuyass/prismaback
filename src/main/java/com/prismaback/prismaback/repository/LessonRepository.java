package com.prismaback.prismaback.repository;

import com.prismaback.prismaback.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    
}
