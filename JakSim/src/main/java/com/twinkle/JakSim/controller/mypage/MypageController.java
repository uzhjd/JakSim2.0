package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.dto.account.LoginLogDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import com.twinkle.JakSim.model.service.account.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
//@PreAuthorize("authentication")
public class MypageController {
    private final String defaultPath = "/content/mypage/";
    private final AccountService accountService;
    private final LoginLogService loginLogService;
    @GetMapping("/auth")
    public String authPage(Model model){
        model.addAttribute("head_title", "개인페이지");
        return String.format(defaultPath + "auth");
    }

    @GetMapping("/{username}")
    public String mainPage(@PathVariable("username") String username, Model model){
        model.addAttribute("head_title", "개인페이지");
        model.addAttribute("user_info", accountService.findByUsername(username));
        LoginLogDto logDto = loginLogService.findByUsernameRecent(username);
        if(logDto.getIp() != null)
            model.addAttribute("log", logDto);
        return String.format(defaultPath + "mypage");
    }

    @GetMapping("/{userId}/history")
    public String logPage(@PathVariable("userId") String username, Model model){
        model.addAttribute("head_title", username + "님 이력확인");
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("access_log", loginLogService.findByUsername(username));
        return String.format(defaultPath + "log");
    }

    @GetMapping("/{userId}/profile")
    public String profilePage(@PathVariable("userId") String username, Model model){
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("log", loginLogService.findByUsernameRecent(username));
        return String.format(defaultPath + "profile");
    }
}
