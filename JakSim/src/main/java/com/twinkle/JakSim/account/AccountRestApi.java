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
        // 1. 데이터 결함 확인 -> null 존재 확인
//        if(data.values().stream().noneMatch(value -> value.equals(""))) {
//            return -1;
//        }
        // 2. 데이터 전달 -> 정상 == true / 비정상 == false
        return accountService.CreateMember(data);
    }
}
