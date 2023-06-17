package com.twinkle.JakSim.controller.account;

import com.twinkle.JakSim.config.auth.CustomUserDetailService;
import com.twinkle.JakSim.model.dto.account.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginRestApi {
    @PostMapping("/login/test")
    public String test(@RequestBody HashMap<Object, String> data){
        System.out.println(data.toString());
        return "redirect: /login/action";
    }
}
