package com.prismaback.prismaback.DTO;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String text;
    private List<String> options;
    private Integer correctOptionIndex;
}