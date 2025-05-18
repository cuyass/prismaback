package com.prismaback.prismaback.DTO;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {
    private Long id;
    private Long lessonId;
    private List<QuestionDTO> questions;
}

