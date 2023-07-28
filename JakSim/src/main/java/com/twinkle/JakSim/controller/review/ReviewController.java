package com.twinkle.JakSim.controller.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;



    @GetMapping("/registerReview/{trainerIdx}")
    public String registerMyReview(Model model, @PathVariable("trainerIdx") int trainerIdx) {
        model.addAttribute("head_title", "리뷰 등록");
        model.addAttribute("trainerIdx", trainerIdx);

        return "content/review/registerReview";
    }
    @PostMapping("/registerReview/{trainerIdx}")
    public String registerReview(@PathVariable("trainerIdx") int trainerIdx, @AuthenticationPrincipal User info,
                                 ReviewRequestDto reviewRequestDto) {
        reviewService.insertReview(reviewRequestDto, info.getUsername(), trainerIdx);

        return "redirect:/";
    }

    @GetMapping("/editReview/{reviewIdx}")
    public String editReview(@PathVariable("reviewIdx") int reviewIdx, Model model, @AuthenticationPrincipal User info,
                             ReviewRequestDto reviewRequestDto) {
        model.addAttribute("head_title", "리뷰 수정");
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReview(info.getUsername()));

        return "content/review/editReview";
    }

    @PostMapping("/editMyReview")
    public String editMyReview(Model model, @AuthenticationPrincipal User info,
                               ReviewRequestDto reviewRequestDto) {
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReview(info.getUsername()));
        reviewService.editReview(reviewRequestDto, info.getUsername());

        return "redirect:/trainer/trainerSearch";
    }

//    @PostMapping("/deleteReview")
//    public String deleteMyReview(@RequestParam("trainerId") int trainerId, @AuthenticationPrincipal User info) {
//        reviewService.deleteReview(info.getUsername());
//        System.out.println(trainerId);
//
//        return "redirect:/trainer/trainerSearch";
//    }

    @GetMapping("/review/list")
    public String getList(){
        return "content/review/review_list";
    }
}
