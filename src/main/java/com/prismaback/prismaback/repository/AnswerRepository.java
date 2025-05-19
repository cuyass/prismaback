package com.prismaback.prismaback.repository;

import com.prismaback.prismaback.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}