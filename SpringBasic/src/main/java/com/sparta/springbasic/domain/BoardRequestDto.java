package com.sparta.springbasic.domain;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
}
