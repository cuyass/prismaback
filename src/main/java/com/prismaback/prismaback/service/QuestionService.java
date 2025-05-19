package com.prismaback.prismaback.service;

import com.prismaback.prismaback.DTO.AnswerDTO;
import com.prismaback.prismaback.DTO.QuestionDTO;
import com.prismaback.prismaback.model.Answer;
import com.prismaback.prismaback.model.Lesson;
import com.prismaback.prismaback.model.Question;
import com.prismaback.prismaback.repository.LessonRepository;
import com.prismaback.prismaback.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final LessonRepository lessonRepository;

    public QuestionDTO createQuestion(Long lessonId, QuestionDTO dto) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lliçó no trobada"));

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

    public QuestionDTO updateQuestion(Long id, QuestionDTO dto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no trobada"));
    
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
    

    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new RuntimeException("Pregunta no trobada");
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
