package com.twinkle.JakSim.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class AccountController {
    private final String defaultPath = "content/account/";
    @GetMapping("/account")
    public String accountPage(Model model){
        model.addAttribute("head_title", "회원가입");
        return String.format(this.defaultPath + "account");
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("head_title", "로그인");
        return String.format(this.defaultPath + "login");
    }

    @GetMapping("/find")
    public String findPage(Model model){
        model.addAttribute("head_title", "아이디/비밀번호 찾기");
        return String.format(this.defaultPath + "find");
    }

    @GetMapping("/test")
    public String testPage(Model model){
        model.addAttribute("head_title", "Test");
        return String.format(this.defaultPath + "test");
    }

    @GetMapping("/account/delprocess")
    public String deleteProcess(){
        return "redirect:/logout";
    }
}
