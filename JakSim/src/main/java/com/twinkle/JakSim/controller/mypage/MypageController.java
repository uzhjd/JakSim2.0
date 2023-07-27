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

    /**
     * <h1>GPT의 조언</h1>
     * - 다음과 같은 고려사항을 살펴보시고 판단해주세요(팀원과의 확인 후에는 삭제해주세요)
     * <ol>
     *     <li>데이터의 양</li>
     *     <li>성능 요구사항</li>
     *     <li>비즈니스 로직 복잡성</li>
     *     <li>코드의 유연성과 확장성</li>
     * </ol>
     * <p>작성자의 판단</p>
     * <span>
     *     보기 나름이지만, 본인의 생각으로는 이 정도 수준의 jdbc 통신으로 네트워크 오버헤드가 발생한다고 판단하기 어렵다 봅니다.
     *     개인적인 생각으로는 코드의 유연성, 비즈니스 로직의 복잡성을 이유로 현재와 같이 소스코드를 유지하는 것도 방법이라고 판단해서 남겨놓습니다.
     * </span>
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}")
    public String mainPage(@PathVariable("username") String username, Model model){
        model.addAttribute("head_title", "개인페이지");
        model.addAttribute("user_info", accountService.findByUsername(username));
        model.addAttribute("log", loginLogService.findByUsernameRecent(username));
        paymentService.getRecentPaymentBy3(username).ifPresent(item -> {
            if(!item.isEmpty()){
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
