package com.prismaback.prismaback.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.DTO.QuizDTO;
import com.prismaback.prismaback.DTO.EvaluationResultDTO;
import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.model.Question;
import com.prismaback.prismaback.model.Quiz;
import com.prismaback.prismaback.repository.LessonRepository;
import com.prismaback.prismaback.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;

    public QuizDTO createQuiz(Long lessonId, QuizDTO quizDTO) {
        Lesson lesson = lessonRepository.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lliçó no trobada"));

        Quiz quiz = new Quiz();
        quiz.setLesson(lesson);

        List<Question> questionEntities = quizDTO.getQuestions().stream()
                .map(dto -> Question.builder()
                        .text(dto.getText())
                        .options(dto.getOptions())
                        .correctOptionIndex(dto.getCorrectOptionIndex())
                        .quiz(quiz)
                        .build())
                .collect(Collectors.toList());

        quiz.setQuestions(questionEntities);

        Quiz savedQuiz = quizRepository.save(quiz);
        return mapToDTO(savedQuiz);
    }

    public Optional<QuizDTO> getQuizByLessonId(Long lessonId) {
        return quizRepository.findByLessonId(lessonId)
                .map(this::mapToDTO);
    }

    private QuizDTO mapToDTO(Quiz quiz) {
        return QuizDTO.builder()
                .id(quiz.getId())
                .lessonId(quiz.getLesson().getId())
                .questions(
                        quiz.getQuestions().stream().map(this::mapQuestionToDTO).collect(Collectors.toList()))
                .build();
    }

    private QuestionDTO mapQuestionToDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .text(question.getText())
                .options(question.getOptions())
                .correctOptionIndex(question.getCorrectOptionIndex())
                .build();
    }

    public List<EvaluationResultDTO> evaluateQuiz(Long quizId, List<AnswerDTO> userAnswers) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz no trobat"));

        Map<Long, Integer> answersMap = userAnswers.stream()
                .collect(Collectors.toMap(AnswerDTO::getQuestionId, AnswerDTO::getSelectedIndex));

        return quiz.getQuestions().stream().map(question -> {
            Integer selected = answersMap.get(question.getId());

            return EvaluationResultDTO.builder()
                    .questionId(question.getId())
                    .questionText(question.getText())
                    .selectedIndex(selected)
                    .correctIndex(question.getCorrectOptionIndex())
                    .isCorrect(selected != null && selected.equals(question.getCorrectOptionIndex()))
                    .build();
        }).collect(Collectors.toList());
    }

}
