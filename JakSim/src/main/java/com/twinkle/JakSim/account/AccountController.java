package com.twinkle.JakSim.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String accountPage(Model model){
        model.addAttribute("head_title", "회원가입");
        return "content/account";
    }
}
