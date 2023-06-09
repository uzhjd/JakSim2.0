package com.twinkle.JakSim;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
public class TestController {
    @PostMapping("/index")
    public String testPage(@RequestBody HashMap<Object, String> data){
        System.out.println(data);
        System.out.println(data.get("message"));
        return "redirect:/";
    }
}
