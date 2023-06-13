package com.twinkle.JakSim.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("head_title", "main");
        if(user != null) {
            System.out.println("[mainPage]");
            System.out.println(user.toString());
            model.addAttribute("user", user.getUsername());
        }

        return "content/index";
    }

    @GetMapping("/test")
    public String testPage(){
        return "content/test";
    }


}
