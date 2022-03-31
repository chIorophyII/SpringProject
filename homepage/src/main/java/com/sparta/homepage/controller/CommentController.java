package com.sparta.homepage.controller;

import com.sparta.homepage.dto.CommentsRequestDto;
import com.sparta.homepage.models.Board;
import com.sparta.homepage.models.Comments;
import com.sparta.homepage.repository.BoardRepository;
import com.sparta.homepage.repository.CommentsRepository;
import com.sparta.homepage.security.UserDetailsImpl;
import com.sparta.homepage.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentsRepository commentsRepository;
    private final BoardRepository boardRepository;

    // 댓글 전체 조회
    @GetMapping("/api/homepage/{id}/comment")
    public List<Comments> getContents(@PathVariable Long boardId) {
        // 게시글 번호로 코멘트를 조회해 해당하는 코멘트만 리턴
        return commentsRepository.findByBoardIdOrderByModifiedAtDesc(boardId);
    }

//    @PostMapping("/api/homepage/{id}/comment")
//    public String createComment(@AuthenticationPrincipal UserDetailsImpl, @PathVariable Long id, @ModelAttribute CommentsRequestDto requestDto) {
//
//    }
    // 댓글 생성
    @PostMapping("/api/homepage/{id}/comment")
    public Comments createContents(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentsRequestDto requestDto, @PathVariable Long id) {
        boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));

        requestDto.setBoardId(id);

        // 생성시 아이디를 받아 저장
//        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUsername();
        Comments comments = new Comments(requestDto, username);

        return commentsRepository.save(comments);
    }

//    @PostMapping("/api/board/{id}/comment")
//    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
//
//        boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("null"));
//
//        requestDto.setWriter(userDetails.getUsername());
//        requestDto.setBoardId(id);
//
//        Comment comment= new Comment(requestDto);
//        System.out.println(comment);
//        commentRepository.save(comment);
//        return comment;
//    }

}
