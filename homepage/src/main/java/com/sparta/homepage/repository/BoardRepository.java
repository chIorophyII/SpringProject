package com.sparta.homepage.repository;

import com.sparta.homepage.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
}