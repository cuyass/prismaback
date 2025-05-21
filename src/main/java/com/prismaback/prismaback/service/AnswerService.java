package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.model.Answer;
import com.prismaback.prismaback.model.Question;
import com.prismaback.prismaback.repository.AnswerRepository;
import com.prismaback.prismaback.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public List<AnswerDTO> getAnswersByQuestionId(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada con id " + questionId));

        return answerRepository.findByQuestion(question).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AnswerDTO createAnswer(Long questionId, AnswerDTO dto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Pregunta no encontrada con id " + questionId));

        Answer answer = Answer.builder()
                .question(question)
                .text(dto.getText())
                .correct(dto.isCorrect())
                .build();

        Answer saved = answerRepository.save(answer);

        return toDTO(saved);
    }
    private AnswerDTO toDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .text(answer.getText())
                .correct(answer.isCorrect())
                .build();
    }
}
