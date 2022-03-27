package com.sparta.homepage.repository;

import com.sparta.homepage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // user table에서 username을 찾겠다
    Optional<User> findByUsername(String username);
}
