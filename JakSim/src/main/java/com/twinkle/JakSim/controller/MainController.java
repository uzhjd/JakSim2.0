package com.twinkle.JakSim.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal User info) {
        model.addAttribute("head_title", "main");
        if(info != null)
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));

        return "content/index";
    }

}
