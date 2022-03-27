package com.sparta.homepage.controller;

import com.sparta.homepage.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}