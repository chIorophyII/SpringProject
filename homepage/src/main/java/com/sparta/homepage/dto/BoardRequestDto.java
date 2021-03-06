package com.sparta.homepage.dto;

import com.sparta.homepage.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
}