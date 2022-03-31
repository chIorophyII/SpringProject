package com.sparta.homepage.controller;

import com.sparta.homepage.dto.BoardRequestDto;
import com.sparta.homepage.models.Board;
import com.sparta.homepage.repository.BoardRepository;
import com.sparta.homepage.security.UserDetailsImpl;
import com.sparta.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    @GetMapping("/api/homepage")
    public List<Board> getContents() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/homepage/{id}")
    public Board getContents(@PathVariable Long id) {
        return boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
    }


    // 게시글 생성
    @PostMapping("/api/homepage")
    public Board createContents(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

//    // 게시글 수정
//    @PutMapping("/api/homepage/{id}")
//    public Long updateContents(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
//        boardService.update(id, requestDto);
//        return id;
//    }

//    @DeleteMapping("/api/homepage/{id}")
//    public Long deleteContents(@PathVariable Long id) {
//        boardRepository.deleteById(id);
//        return id;
//    }

}