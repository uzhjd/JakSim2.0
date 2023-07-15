package com.twinkle.JakSim.controller;

import com.twinkle.JakSim.model.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    TrainerService trainerService;
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("head_title", "작심득근");
        model.addAttribute("trainers", trainerService.searchTrainerForMainPage());
        return "content/index";
    }

}
