package com.twinkle.JakSim;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal Principal principal){
        model.addAttribute("head_title", "main");
        if(principal.getName() != null)
            System.out.println(principal.getName() + " :: " + principal.toString());
        return "content/index";
    }


}
