package com.twinkle.JakSim.controller.administrator.info;

import com.twinkle.JakSim.model.service.account.AccountService;
import com.twinkle.JakSim.model.service.account.FileService;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Access Only ADMINISTRATOR
 */
@Controller
@RequiredArgsConstructor
public class InfoController {
    private final AccountService accountService;
    private final FileService fileService;
    private final ReviewService reviewService;
    private final PaymentService paymentService;
    private final String defaultPath = "content/administrator/info/";
    @GetMapping("/info/{username}")
    public String infoPage(@PathVariable("username") String username, Model model){
        model.addAttribute("user", accountService.findByUsername(username));
        model.addAttribute("imageList", fileService.getAllImages(username));
        model.addAttribute("reviewList", reviewService.showMyReview(username));
        model.addAttribute("paymentList", paymentService.getPageItem(username, 1));

        return String.format(defaultPath+"info");
    }
}
