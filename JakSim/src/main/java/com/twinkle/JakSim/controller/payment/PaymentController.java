package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDo;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final KakaoPayService kakaoPayService;
    private final PaymentService paymentService;
    private final String defaultPage = "/content/payment/";

    @GetMapping("/cancel")
    public String cancel() {
        return defaultPage + "kakaoPay/Cancle";
    }

    @GetMapping("/success")
    public String afterPayRequest(@AuthenticationPrincipal User user, @RequestParam("pg_token") String pgToken,
                                                                                                        Model model) {
        ApproveResponse kakaoApprove = kakaoPayService.approveResponse(user.getUsername(), pgToken);

        if(kakaoApprove != null) {
            if(paymentService.savePaymentDetails(user.getUsername(), kakaoApprove)) {
                kakaoApprove.setApproved_at(kakaoApprove.getApproved_at().replace("T", " "));

                model.addAttribute("kakaoApprove", kakaoApprove);

                return defaultPage + "kakaoPay/Success";
            }
        }

        return defaultPage + "kakaoPay/Success";
    }

    @GetMapping("/refundSuccess/{tid}")
    public String approveRefund(@PathVariable("tid") String tid, Model model) {
       paymentService.refund(tid).ifPresent(
                item-> {
                    model.addAttribute("refund", item);
                }
        );

        return defaultPage + "kakaoPay/Refund";
    }

    @GetMapping("/list")
    public String payListPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("head_title", "결제 확인 목록");
        //전체 페이지 수까지 axios로 받을 시, async 특성 때문에 정상적이지 않을수도 있음
        //따라서, 전체 페이지수 만큼은 Controller에서 받아내도록 함 -> 필요시 변경 가능
        model.addAttribute("total_page", paymentService.getTotalPage(user.getUsername()));

        return String.format(defaultPage + "pay_list");
    }

    @GetMapping("/detail/{tid}")
    public String payDetailPage(@AuthenticationPrincipal User user, @PathVariable("tid") String tid, Model model) {
        model.addAttribute("head_title", "결제 내역 상세");

        paymentService.getPaymentByIdx(tid).ifPresent(
                item -> {
                    model.addAttribute("apiResponse", kakaoPayService.kakaoList(tid));
                    model.addAttribute("payment", item);
                    model.addAttribute("product", paymentService.getProductByIdx(item.getTp_idx()));
                }
        );

        return String.format(defaultPage + "pay_detail");
    }

    @GetMapping("/api/{page}")
    @ResponseBody
    public List<PaymentDo> payListItem(@AuthenticationPrincipal User user, @PathVariable("page") int page){
        return paymentService.getPageItem(user.getUsername(), page);
    }
    
}
