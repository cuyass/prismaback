package com.prismaback.prismaback.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResultDTO {
    private Long questionId;
    private String questionText;
    private Integer selectedIndex;
    private Integer correctIndex;
    private Boolean isCorrect;
}

