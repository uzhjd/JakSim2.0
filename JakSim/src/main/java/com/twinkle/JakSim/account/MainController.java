package com.twinkle.JakSim.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("Jaemok", "index");
        return "content/index";
    }
}
