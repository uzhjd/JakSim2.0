package com.twinkle.JakSim.controller.account;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRestApi {
    private final AccountService accountService;

    @PostMapping("/action")
    public int AccountAction(@RequestBody UserDto data){
        return accountService.CreateMember(data);
    }

    @PostMapping("/checkid")
    public boolean checkId(@RequestBody UserDto data){
        return accountService.findByUsername(data.getId()) == null;
    }

    @PostMapping("/checktel")
    public boolean checkTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel()) == null;
    }

    @PostMapping("/checkemail")
    public boolean dupEmail(@RequestBody UserDto data){
        return accountService.findByEmail(data.getEmail()) == null;
    }

    @PostMapping("/findtel")
    public UserDto findTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel());
    }

    @PutMapping("/changepw")
    public int updatePassword(@RequestBody UserDto data){
        return accountService.update(data.getId(), data.getPw());
    }
}
