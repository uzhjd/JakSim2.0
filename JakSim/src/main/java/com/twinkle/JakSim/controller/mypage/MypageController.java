package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.service.account.AccountService;
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
    @GetMapping("/auth")
    public String authPage(Model model){
        model.addAttribute("head_title", "개인페이지");
        return String.format(defaultPath + "auth");
    }

    @GetMapping("/{username}")
    public String myPage(@PathVariable("username") String username, @AuthenticationPrincipal User user, Model model){
        model.addAttribute("head_title", "개인페이지");
        model.addAttribute("user_info", accountService.findByUsername(user.getUsername()));
        return String.format(defaultPath + "mypage");
    }

}
