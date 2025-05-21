package com.prismaback.prismaback.repository;

import com.prismaback.prismaback.model.Answer;
import com.prismaback.prismaback.model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
}