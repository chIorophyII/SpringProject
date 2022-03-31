package com.sparta.homepage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.homepage.dto.UserRequestDto;
import com.sparta.homepage.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid UserRequestDto requestDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시 입력 데이터 유지
            model.addAttribute("userDto", requestDto);
            // 유효성 통과 못한 필드와 메세지를 핸들링
            Map<String, String> validationResult = userService.validationHandling(errors);
            for (String key : validationResult.keySet()) {
                model.addAttribute(key, validationResult.get(key));
            }
            return "signup";
        }

        // 중복 검사 후 메세지
        try {
            userService.registerUser(requestDto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

//        userService.registerUser(userDto);
        return "login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaologin(@RequestParam String code) throws JsonProcessingException {
        userService.kakaoLogin(code);
        return "redirect:/";
    }
}
