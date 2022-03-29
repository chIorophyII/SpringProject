package com.sparta.homepage.controller;

import com.sparta.homepage.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

//    @GetMapping("/blogs/{id}")
//    public String readBlog(@PathVariable Long id, Model md, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        md.addAttribute("response", blogRepository.findById(id).orElseThrow( () -> new NullPointerException("게시물이 없습니다.")));
//        return "detail";
//    }


}
