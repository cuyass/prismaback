package com.prismaback.prismaback.DTO;

import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String text;
    private Long lessonId;
    @Builder.Default
    private List<AnswerDTO> answers = new ArrayList<>();
}