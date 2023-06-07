package com.twinkle.JakSim.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("Jaemok", "index");
        return "content/index";
    }

    @PostMapping("/index")
    public String testPage(@RequestBody String message){
        System.out.println(message);
        return "redirect:/";
    }
}
