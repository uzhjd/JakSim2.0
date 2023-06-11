package com.twinkle.JakSim.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String accountPage(Model model){
        model.addAttribute("head_title", "회원가입");
        return "content/account";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("head_title", "로그인");
        return "content/login";
    }
}
