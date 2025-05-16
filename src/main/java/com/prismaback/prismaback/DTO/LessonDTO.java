package com.prismaback.prismaback.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDTO {
    
    private Long id;
    private String title;
    private String content;
}
