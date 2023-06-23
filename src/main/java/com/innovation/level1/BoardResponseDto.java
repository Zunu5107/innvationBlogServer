package com.innovation.level1;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDto {
    private long boardId;
    private String title;
    private String username;
    private String content;
    private String createdAt;
}
