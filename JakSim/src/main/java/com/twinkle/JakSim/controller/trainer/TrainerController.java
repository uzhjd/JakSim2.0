package com.twinkle.JakSim.controller.trainer;

import org.springframework.web.bind.annotation.GetMapping;

public class TrainerController {

    @GetMapping("/trainer/register")
    public String trainerRegisterForm(){

        return "content/trainer/trainerRegister";
    }
}
