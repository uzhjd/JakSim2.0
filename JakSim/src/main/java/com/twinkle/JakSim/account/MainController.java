package com.twinkle.JakSim.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("head_title", "main");
        return "content/index";
    }

    @PostMapping("/index")
    @ResponseBody
    public boolean testPage(@RequestBody HashMap<Object, String> message){
        System.out.println(message);
        System.out.println(message.get("name"));
        System.out.println(message.get("number"));

        return !message.values().stream().anyMatch(value -> value.equals(""));
    }
}
