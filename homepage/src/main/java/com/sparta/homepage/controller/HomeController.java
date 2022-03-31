package com.sparta.homepage.controller;

import com.sparta.homepage.models.Board;
import com.sparta.homepage.models.Comments;
import com.sparta.homepage.repository.BoardRepository;
import com.sparta.homepage.repository.CommentsRepository;
import com.sparta.homepage.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final BoardRepository boardRepository;
    private final CommentsRepository commentsRepository;

    public HomeController(BoardRepository boardRepository, CommentsRepository commentsRepository) {
        this.boardRepository = boardRepository;
        this.commentsRepository = commentsRepository;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    // 게시글 특정 조회
    @GetMapping("/detail")
    public String board() {
        return "detail";
    }
}
