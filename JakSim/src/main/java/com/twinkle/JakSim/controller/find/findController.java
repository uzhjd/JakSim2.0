package com.twinkle.JakSim.controller.find;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class findController {
    private final String defaultPath = "content/find/";
    @GetMapping("/find/userid")
    public String findIdPage(Model model){
        model.addAttribute("head_title", "아이디 찾기");

        return String.format(defaultPath + "find_userid");
    }

    @GetMapping("/find/userid/cert")
    public String certIdPage(Model model){
        model.addAttribute("head_title", "이메일 인증");
        return String.format(defaultPath + "find_userid_cert");
    }
}
