package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.Enum.ErrorCode;
import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.dto.payment.response.CancelResponse;
import com.twinkle.JakSim.model.dto.payment.response.ReadyResponse;
import com.twinkle.JakSim.model.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public ReadyResponse readyToKakaoPay() {

        return kakaoPayService.kakaoPayReady();
    }
    /**
     * 결제 성공
     */
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        ApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {

        throw new BusinessLogicException(ErrorCode.PAY_CANCEL);
    }


    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {
        throw new BusinessLogicException(ErrorCode.PAY_FAILED);
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