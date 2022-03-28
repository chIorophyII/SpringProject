package com.sparta.homepage.service;

import com.sparta.homepage.dto.SignupRequestDto;
import com.sparta.homepage.models.User;
import com.sparta.homepage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        User user = new User(username, password);
        userRepository.save(user);
    }
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
//// 회원 ID 중복 확인
//        String username = requestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//        }
//
//// 패스워드 암호화
//        String password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();
//
//// 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }
//
//        User user = new User(username, password, email, role);
//        userRepository.save(user);
//    }
//}
