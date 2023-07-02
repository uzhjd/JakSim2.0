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
        System.out.println(data.toString());
        return accountService.CreateMember(data);
    }

    @GetMapping("/user-info")
    public UserDetails getUserInfo(@AuthenticationPrincipal User user){
        return user;
    }

    @PostMapping("/checkid")
    public int checkId(@RequestBody UserDto data){
        return accountService.findByUsername(data.getId()) == null ? 0 : 1;
    }

    @PostMapping("/checktel")
    public int checkTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel()) == null ? 0 : 1;
    }

    @PostMapping("/emailaction")
    public String checkEmail(@RequestBody UserDto data){
        return accountService.validateEmail(data.getEmail());
    }

    @PostMapping("/findtel")
    public UserDto findTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel());
    }

    @PutMapping("/changepw")
    public int updatePassword(@RequestBody UserDto data){
        return accountService.update(data.getId(), data.getPw());
    }

    @DeleteMapping("/delete")
    public int deleteUser(@AuthenticationPrincipal User user){
        return accountService.delete(user.getUsername());
    }
}
