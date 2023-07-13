package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.Enum.ErrorCode;
import com.twinkle.JakSim.model.dto.payment.request.PaymentRequest;
import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.dto.payment.response.CancelResponse;
import com.twinkle.JakSim.model.dto.payment.response.ReadyResponse;
import com.twinkle.JakSim.model.service.payment.KakaoPayService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayRestController {

    private final KakaoPayService kakaoPayService;
    private final PaymentService paymentService;

    // 결제 요청
    @PostMapping("/ready")
    public ReadyResponse readyToKakaoPay(@AuthenticationPrincipal User user,
                                         @Valid @RequestBody PaymentRequest paymentRequest) {
        return kakaoPayService.kakaoPayReady(user.getUsername(), paymentRequest);
    }

    // 결제 승인 (성공)
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@AuthenticationPrincipal User user, @RequestParam("pg_token") String pgToken) {
        ApproveResponse kakaoApprove = kakaoPayService.approveResponse(user.getUsername(), pgToken);

        if(kakaoApprove != null) {
            System.out.println(kakaoApprove);
//            if(paymentService.savePaymentDetails(user.getUsername(), kakaoApprove)) {
//                return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
//
//            }
        }

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {
//        throw new BusinessLogicException(ErrorCode.PAY_FAILED);
    }

    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund() {
        CancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }
}