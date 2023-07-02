package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/mypage/api")
@RequiredArgsConstructor
public class MypageRestApi {
    private final AccountService accountService;
    @PostMapping("/auth")
    public String authPassword(@RequestBody UserDto userDto, @AuthenticationPrincipal User user){
        return accountService.checkPassword(user.getUsername(), userDto.getPw()) ? user.getUsername() : null;
    }
    @GetMapping("/sessiontime")
    public int getSessionTime(HttpSession session){
        return session.getMaxInactiveInterval();
    }
}
