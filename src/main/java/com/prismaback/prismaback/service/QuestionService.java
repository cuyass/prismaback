package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.exception.lesson.LessonNotFoundException;
import com.prismaback.prismaback.exception.question.QuestionNotFoundException;
import com.prismaback.prismaback.exception.question.QuestionValidationException;
import com.prismaback.prismaback.model.Answer;
import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.model.Question;
import com.prismaback.prismaback.repository.LessonRepository;
import com.prismaback.prismaback.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    public QuestionDTO createQuestion(Long lessonId, QuestionDTO dto) {
        if (dto.getText() == null || dto.getText().isBlank()) {
            throw new QuestionValidationException("El text de la pregunta és obligatori");
        }
        if (dto.getAnswers() == null || dto.getAnswers().isEmpty()) {
            throw new QuestionValidationException("Cal afegir almenys una resposta");
        }

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(lessonId));

        Question question = new Question();
        question.setText(dto.getText());
        question.setLesson(lesson);

        List<Answer> answers = dto.getAnswers().stream()
                .map(answerDTO -> Answer.builder()
                        .text(answerDTO.getText())
                        .correct(answerDTO.isCorrect())
                        .question(question)
                        .build())
                .collect(Collectors.toList());

        question.setAnswers(answers);

        return toDTO(questionRepository.save(question));
    }

    public List<QuestionDTO> getQuestionsByLessonId(Long lessonId) {
        List<Question> questions = questionRepository.findByLessonId(lessonId);
        return questions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO updateQuestion(Long questionId, QuestionDTO dto) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(questionId));
    
        existingQuestion.setText(dto.getText());
    
        existingQuestion.getAnswers().clear();
    
        List<Answer> updatedAnswers = dto.getAnswers().stream()
                .map(answerDTO -> Answer.builder()
                        .text(answerDTO.getText())
                        .correct(answerDTO.isCorrect())
                        .question(existingQuestion)
                        .build())
                .collect(Collectors.toList());
    
        existingQuestion.getAnswers().addAll(updatedAnswers);
    
        return toDTO(questionRepository.save(existingQuestion));
    }
    
@Transactional
    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new QuestionNotFoundException(questionId);
        }
        questionRepository.deleteById(questionId);
    }

    private QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .text(question.getText())
                .lessonId(question.getLesson().getId())
                .answers(
                    question.getAnswers().stream()
                        .map(a -> AnswerDTO.builder()
                                .id(a.getId())
                                .text(a.getText())
                                .correct(a.isCorrect())
                                .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
