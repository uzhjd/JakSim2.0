package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.dto.account.LoginLogDto;
import com.twinkle.JakSim.model.dto.payment.PaymentDo;
import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.dto.trainer.ProductDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import com.twinkle.JakSim.model.service.account.LoginLogService;
import com.twinkle.JakSim.model.service.inbody.InbodyService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.review.ReviewService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
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
    private final PaymentService paymentService;
    private final ReviewService reviewService;
    private final TrainerService trainerService;
    private final InbodyService inbodyService;

    @GetMapping("/auth")
    public String authPage(Model model){
        model.addAttribute("head_title", "개인페이지");
        return String.format(defaultPath + "auth");
    }

    @GetMapping("/{username}")
    public String mainPage(@PathVariable("username") String username, Model model){
        model.addAttribute("head_title", "개인페이지");
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("log", loginLogService.findByUsernameRecent(username));

        //payment 관련 서비스가 더 존재하는지 확인바람
        PaymentDo paymentDo = paymentService.getRecentPayment(username);
        ProductDto productDto = trainerService.getProductByTrainerIdx(paymentDo.getTp_idx());
        TrainerDetailDto trainerDto = trainerService.findMyTrainer(String.format(""+ productDto.getPtId()));
        System.out.println(trainerDto.toString());
        model.addAttribute("payment", paymentDo);
        model.addAttribute("product", productDto);
        model.addAttribute("product_trainer");


        ReviewRequestDto review = reviewService.showMyReivew(username).get(0);
        model.addAttribute("review", review);

        return String.format(defaultPath + "mypage");
    }

    @GetMapping("/{userId}/history/login")
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

    @GetMapping("/{userId}/history/inbody")
    public String weightPage(@PathVariable("userId") String username, Model model){
        model.addAttribute("head_title", "체중 그래프");
        model.addAttribute("access_log", loginLogService.findByUsernameRecent(username));
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("inbody_log", inbodyService.getInbodies(username));

        return String.format(defaultPath + "inbody/body_info");
    }
}
