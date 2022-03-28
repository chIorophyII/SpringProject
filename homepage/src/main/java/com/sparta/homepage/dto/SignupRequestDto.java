package com.sparta.homepage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{3,16}", message = "아이디는 최소 3자 이상, 영문 대소문자, 숫자를 사용하세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,16}", message = "비밀번호는 최소 4자 이상, 영문 대소문자, 숫자를 사용하세요.")
    private String password;

//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
//    @NotBlank(message = "이메일은 필수 입력 값입니다.")
//    private String email;

//    private boolean admin = false;
//    private String adminToken = "";
}