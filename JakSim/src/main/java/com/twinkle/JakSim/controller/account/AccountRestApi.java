package com.twinkle.JakSim.controller.account;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRestApi {
    private final AccountService accountService;

    @PostMapping("/action")
    public int AccountAction(@RequestBody HashMap<Object, String> data){
        if(data.values().stream().noneMatch(value -> value.equals(""))) {
            return -1;
        }
        return accountService.CreateMember(data);
    }

    @GetMapping("/user-info")
    public UserDetails getUserInfo(@AuthenticationPrincipal User user){
        return user;
    }

    @PostMapping("/checkid")
    public int checkId(@RequestBody UserDto data){
        return accountService.findByUsername(data.getId());
    }

    @PostMapping("/checktel")
    public int checkTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel());
    }
}
