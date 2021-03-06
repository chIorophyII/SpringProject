package com.sparta.homepage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homepage.dto.KakaoUserInfoDto;
import com.sparta.homepage.dto.UserRequestDto;
import com.sparta.homepage.models.User;
import com.sparta.homepage.repository.UserRepository;
import com.sparta.homepage.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Map<String, String> validationHandling(Errors errors) {
        Map<String, String> validationResult = new HashMap<>();

        for (FieldError error: errors.getFieldErrors()) {
            String validkeyName = String.format("valid_%s", error.getField());
            validationResult.put(validkeyName, error.getDefaultMessage());
        }
        return validationResult;
    }
    // bindingresult

    public void registerUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        // ?????? ID ?????? ??????
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("????????? ID??? ???????????????.");
        }
        // ???????????? ?????? ??????
        if(!requestDto.getPassword().equals(requestDto.getCheckpw())) {
            throw new IllegalArgumentException("??????????????? ???????????? ????????????.");
        }

        // ???????????? ?????????
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // ????????? ROLE ??????
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("????????? ????????? ?????? ????????? ??????????????????.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        User user = new User(username, email, password);
        userRepository.save(user);
    }

    public void kakaoLogin(String code) throws JsonProcessingException {
        // 1. "?????? ??????"??? "????????? ??????" ??????
        String accessToken = getAccessToken(code);

        // 2. ???????????? ????????? API ??????
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

        // DB ??? ????????? Kakao Id ??? ????????? ??????
        Long kakaoId = kakaoUserInfo.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);
        if (kakaoUser == null) {
            // ????????????
            // username: kakao nickname
            String nickname = kakaoUserInfo.getNickname();

            // password: random UUID
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);

            // email: kakao email
            String email = kakaoUserInfo.getEmail();
            // role: ?????? ?????????
//            UserRoleEnum role = UserRoleEnum.USER;

            kakaoUser = new User(nickname, email, encodedPassword, kakaoId);
            userRepository.save(kakaoUser);
        }

        // 4. ?????? ????????? ??????
        UserDetails userDetails = new UserDetailsImpl(kakaoUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body ??????
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "461c917f7343f05a0bfe72bb4ae6b40a");
        body.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
        body.add("code", code);

        // HTTP ?????? ?????????
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP ?????? (JSON) -> ????????? ?????? ??????
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP ?????? ?????????
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();

        System.out.println("????????? ????????? ??????: " + id + ", " + nickname + ", " + email);

        return new KakaoUserInfoDto(id, nickname, email);
    }


//    public void checkUser(SignupRequestDto requestDto) {
//        String username = requestDto.getUsername();
//    }
}

//import com.sparta.springcore.dto.SignupRequestDto;
//        import com.sparta.springcore.model.User;
//        import com.sparta.springcore.model.UserRoleEnum;
//        import com.sparta.springcore.repository.UserRepository;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.security.crypto.password.PasswordEncoder;
//        import org.springframework.stereotype.Service;
//
//        import java.util.Optional;
//
//@Service
//public class UserService {
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
//
//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void registerUser(SignupRequestDto requestDto) {
//// ?????? ID ?????? ??????
//        String username = requestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("????????? ????????? ID ??? ???????????????.");
//        }
//
//// ???????????? ?????????
//        String password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();
//
//// ????????? ROLE ??????
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("????????? ????????? ?????? ????????? ??????????????????.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }
//
//        User user = new User(username, password, email, role);
//        userRepository.save(user);
//    }
//}