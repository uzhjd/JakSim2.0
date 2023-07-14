package com.twinkle.JakSim.controller.payment;

import com.twinkle.JakSim.model.dto.payment.PaymentDtoForMypage;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final String defaultPage = "/content/payment/";
    private final PaymentService paymentService;
    private final TrainerService trainerService;
    @GetMapping("/pay/list")
    public String payListPage(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("head_title", "결제 확인 목록");
        //전체 페이지 수까지 axios로 받을 시, async 특성 때문에 정상적이지 않을수도 있음
        //따라서, 전체 페이지수 만큼은 Controller에서 받아내도록 함 -> 필요시 변경 가능
        model.addAttribute("total_page", paymentService.getTotalPage(user.getUsername()));

        return String.format(defaultPage + "pay_list");
    }

    @GetMapping("/pay/detail/{idx}")
    public String payDetailPage(@AuthenticationPrincipal User user, @PathVariable("idx") int p_idx, Model model){
        model.addAttribute("head_title", "결제 내역 상세");
        paymentService.getPaymentByIdx(p_idx).ifPresent(
                item -> {
                    model.addAttribute("payment", item);
                    model.addAttribute("product", paymentService.getProductByIdx(item.getTp_idx()));
                }
        );
        return String.format(defaultPage + "pay_detail");
    }

    @GetMapping("/pay/api/{page}")
    @ResponseBody
    public List<PaymentDtoForMypage> payListItem(@AuthenticationPrincipal User user, @PathVariable("page") int page){
        return paymentService.getPageItem(user.getUsername(), page);
    }
}
