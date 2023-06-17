package com.twinkle.JakSim.controller.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainerController {

    @GetMapping("/trainer/register")
    public String trainerRegisterForm() {

        return "content/trainer/trainerRegister";
    }

    @GetMapping("/trainer/search")
    public String trainerPage(Model model) {
        model.addAttribute("head_title", "트레이너 검색");
        return "content/trainer/trainerSearch";
    }
}
