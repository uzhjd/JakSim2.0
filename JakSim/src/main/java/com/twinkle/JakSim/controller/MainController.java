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
    public String mainPage(Principal user, Model model) {
        System.out.println("[mainPage]");
        model.addAttribute("head_title", "main");
        if(user != null) {
            System.out.println("[mainPage]");
            System.out.println(user.toString());
            model.addAttribute("user", user);
        }

        return "content/index";
    }

    @GetMapping("/test")
    @ResponseBody
    public Principal testPage(Principal principal, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            int timeoutInSeconds = session.getMaxInactiveInterval();
            System.out.println("유효시간 : " + timeoutInSeconds);
        }
        return principal;
    }

    @GetMapping("/test2")
    @ResponseBody
    public User testPages(@AuthenticationPrincipal User user){
        return user;
    }
}
