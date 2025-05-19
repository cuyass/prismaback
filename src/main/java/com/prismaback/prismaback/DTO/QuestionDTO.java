package com.prismaback.prismaback.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String text;
    private Long lessonId;
    private List<AnswerDTO> answers;
}