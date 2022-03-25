package com.sparta.springbasic.controller;

import com.sparta.springbasic.domain.Board;
import com.sparta.springbasic.domain.BoardRepository;
import com.sparta.springbasic.domain.BoardRequestDto;
import com.sparta.springbasic.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor// new memoRepository, new memoService 도 스프링이 해준다
@RestController // new MemoController를 스프링이 대신 해준다
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 전체 게시글 목록 조회 페이지
    @GetMapping("/api/boards")
    public List<Board> readBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 조회 페이지
    @GetMapping("/api/boards/{id}")
    public Board readDetail(@PathVariable Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null")
        );
    }
    // pathvariable이랑 request param의 차이?

//    @GetMapping("/api/board/{id}")
//    public Board getBoardDetail(@PathVariable Long id) {
//        return boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("null"));
//    }

    // 게시글 작성 페이지
    @PostMapping("/api/boards")
    // RequestBody -> 클라이언트가 보내는 데이터를 requestdto에 넣어줘
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }
//
//    @GetMapping("/post")
//    public String htmlFile() {
//        return "redirect:/post.html";
//    }

//    @DeleteMapping("/boards/{id}")
//    public Long deleteBoard(@PathVariable Long id) {
//        boardRepository.deleteById(id);
//        return id;
//    }
//
//    @PutMapping("/boards/{id}")
//    public Long updateMemo(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
//        boardService.update(id, requestDto);
//        return id;
//    }
}
