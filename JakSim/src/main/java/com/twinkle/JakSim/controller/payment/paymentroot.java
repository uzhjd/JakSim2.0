package com.twinkle.JakSim.controller.payment;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class paymentroot {

    @GetMapping()
    public String scheduler() {
        return "content/payment/KakaoPay";
    }
}
