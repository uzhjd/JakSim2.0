package com.twinkle.JakSim.controller.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/editReview")
    public String editReview(Model model, @AuthenticationPrincipal User info,
                             ReviewRequestDto reviewRequestDto) {
        model.addAttribute("head_title", "리뷰 수정");
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReivew(info.getUsername()));

        return "content/review/editReview";
    }

    @PostMapping("/editReview")
    public String editMyReview(Model model, @AuthenticationPrincipal User info,
                               ReviewRequestDto reviewRequestDto) {
        model.addAttribute("head_title", "리뷰 수정");
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReivew(info.getUsername()));
        reviewService.editReview(reviewRequestDto, info.getUsername());

        return "content/review/editReview";
    }

    @PostMapping("/deleteReview")
    public String deleteMyReview(Model model, @AuthenticationPrincipal User info,
                               ReviewRequestDto reviewRequestDto) {
        model.addAttribute("head_title", "트레이너 상세페이지");
        model.addAttribute("userId", info);
        reviewService.deleteReview(info.getUsername());

        return "content/trainer/trainerDetailPage";
    }


}
