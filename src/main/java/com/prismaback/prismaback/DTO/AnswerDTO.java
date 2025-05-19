package com.prismaback.prismaback.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {
    private Long id;
    private String text;
    private boolean isCorrect;
}
