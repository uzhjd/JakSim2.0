package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.service.account.AccountService;
import com.twinkle.JakSim.model.service.account.LoginLogService;
import com.twinkle.JakSim.model.service.inbody.InbodyService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final String defaultPath = "/content/mypage/";
    private final AccountService accountService;
    private final LoginLogService loginLogService;
    private final PaymentService paymentService;
    private final ReviewService reviewService;
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
        paymentService.getRecentPaymentBy3(username).ifPresent(item -> {
            if(!item.isEmpty()){
                System.out.println(item.toString());
                model.addAttribute("payment", item);
            }
        });
        reviewService.showMyReviewForMyPage(username).ifPresent(reviewRequestDtos -> {if (!reviewRequestDtos.isEmpty()) {
            model.addAttribute("reviewList", reviewRequestDtos);
        }});

        return String.format(defaultPath + "mypage");
    }

    @GetMapping("/{userId}/history/login")
    public String logPage(@PathVariable("userId") String username, Model model){
        model.addAttribute("head_title", username + "님 이력확인");
        model.addAttribute("user_info", accountService.findByUsername(username));

        model.addAttribute("access_log", loginLogService.findByUsername(username, 2));
        return String.format(defaultPath + "log");
    }

    @GetMapping("/{userId}/profile")
    public String profilePage(@PathVariable("userId") String username, Model model){
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("log", loginLogService.findByUsernameRecent(username));
        return String.format(defaultPath + "profile");
    }

    @GetMapping("/{userId}/history/inbody")
    public String inbodyPage(@PathVariable("userId") String username, Model model){
        model.addAttribute("head_title", "인바디");
        model.addAttribute("access_log", loginLogService.findByUsernameRecent(username));
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("totalPage", inbodyService.getTotalPage(username));

        return String.format(defaultPath + "inbody/body_info");
    }
}
