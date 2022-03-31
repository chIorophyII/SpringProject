package com.sparta.homepage.dto;

import com.sparta.homepage.models.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsRequestDto {
    private String username;
    private String contents;
    private Long boardId;
}
