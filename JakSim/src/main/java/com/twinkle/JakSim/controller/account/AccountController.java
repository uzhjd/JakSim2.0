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

    @GetMapping("/account/{num}")
    public String accountPages(@PathVariable("num") int num, Model model){
        model.addAttribute("head_title", "회원가입");
        String pageSeq = String.format(defaultPath + "account_" + num);

        if(num == 4){ //이후에 숫자는 본인 재량껏 변경바람
            model.addAttribute("head_title", "회원가입 성공");
            pageSeq = String.format(defaultPath + "account_fin");
        }

        return pageSeq;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("head_title", "로그인");
        return String.format(this.defaultPath + "login");
    }

    @GetMapping("/account/delprocess")
    public String deleteProcess(){
        return "redirect:/logout";
    }
}
