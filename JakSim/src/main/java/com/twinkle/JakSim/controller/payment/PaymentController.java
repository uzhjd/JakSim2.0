package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.service.payment.KakaoPayService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final KakaoPayService kakaoPayService;
    private final PaymentService paymentService;

    // 결제 진행 중 취소
    @GetMapping()
    public String scheduler() {
        return "content/payment/KakaoPay";
    }

    // 결제 진행 중 취소
    @GetMapping("/cancel")
    public String cancel() {
        return "content/payment/kakaoPay/Cancle";
    }

    // 결제 승인 (성공)
    @GetMapping("/success")
    public String afterPayRequest(@AuthenticationPrincipal User user, @RequestParam("pg_token") String pgToken, Model model) {
        ApproveResponse kakaoApprove = kakaoPayService.approveResponse(user.getUsername(), pgToken);

        if(kakaoApprove != null) {
            System.out.println(kakaoApprove);
            if(paymentService.savePaymentDetails(user.getUsername(), kakaoApprove)) {
                return "content/payment/kakaoPay/Success";
            }
        }

        model.addAttribute("name", kakaoApprove.getItem_name());
        model.addAttribute("cnt", kakaoApprove.getPtTimes());
        model.addAttribute("period", kakaoApprove.getPtPeriod());
        model.addAttribute("price", kakaoApprove.getAmount().getTotal());
        model.addAttribute("method", kakaoApprove.getPayment_method_type());
        model.addAttribute("date", kakaoApprove.getApproved_at().split("T")[0]);

        // 일단은 에러처리 안함.
        return "content/payment/kakaoPay/Success";
    }
}
