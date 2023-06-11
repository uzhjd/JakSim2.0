package com.twinkle.JakSim.account;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
