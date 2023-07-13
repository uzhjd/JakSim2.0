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

    // 결제 진행 중 취소
    @GetMapping()
    public String scheduler() {
        return "content/payment/KakaoPay";
    }

    // 결제 진행 중 취소
    @GetMapping("/cancel")
    public String cancel() {
        return "content/payment/KakaoPayCancle";
    }
}
