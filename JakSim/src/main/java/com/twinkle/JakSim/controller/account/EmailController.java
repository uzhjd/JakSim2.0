package com.twinkle.JakSim.controller.account;

import com.twinkle.JakSim.model.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final AccountService accountService;
    @PostMapping("/email/api/send")
    public String sendMail(@RequestBody HashMap<String, String> data){
        System.out.println(data.get("email"));
        return accountService.validateEmail(data.get("email"));
    }
}
