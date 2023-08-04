package com.twinkle.JakSim.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
    private final String defaultPath = "content/account/";

    @GetMapping("/account")
    public String accountPage(Model model) {
        model.addAttribute("head_title", "회원가입");
        return String.format(this.defaultPath + "account");
    }

    @GetMapping("/account/{num}")
    public String accountPages(@PathVariable("num") int num, Model model) {
        String pageSeq;

        if (num == 4) { //이후에 숫자는 본인 재량껏 변경바람
            model.addAttribute("head_title", "회원가입 성공");
            pageSeq = String.format(defaultPath + "account_fin");
            return pageSeq;
        }

        model.addAttribute("head_title", "회원가입");
        pageSeq = String.format(defaultPath + "account_" + num);

        return pageSeq;
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        model.addAttribute("head_title", "로그인");

        String url = request.getHeader("Referer");

        if (url != null && !url.contains("/login")) {
            request.getSession().setAttribute("prevPage", url);
        }
        return String.format(this.defaultPath + "login");
    }

    @GetMapping("/account/delprocess")
    public String deleteProcess() {
        return "redirect:/logout";
    }
}
