package com.twinkle.JakSim.controller.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.service.account.FileService;
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
    @Autowired
    FileService fileService;


    @GetMapping("/registerReview/{trainerId}")
    public String registerMyReview(Model model, @PathVariable("trainerId") String trainerId, @AuthenticationPrincipal User info) {
        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }
        model.addAttribute("head_title", "리뷰 등록");
        model.addAttribute("trainerId", trainerId);

        return "content/review/registerReview";
    }
    @PostMapping("/registerReview/{trainerIdx}")
    public String registerReview(@PathVariable("trainerIdx") int trainerIdx, @AuthenticationPrincipal User info,
                                 ReviewRequestDto reviewRequestDto) {
        reviewService.insertReview(reviewRequestDto, info.getUsername(), trainerIdx);

        return "redirect:/";
    }

    @GetMapping("/review/editReview/{reviewIdx}")
    public String editReview(@PathVariable("reviewIdx") int reviewIdx, Model model, @AuthenticationPrincipal User info,
                             ReviewRequestDto reviewRequestDto) {
        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "리뷰 수정");
        model.addAttribute("review", reviewService.showMyReview(info.getUsername(), reviewIdx));

        return "content/review/editReview";
    }

    @PostMapping("/editMyReview")
    public String editMyReview(@AuthenticationPrincipal User info, ReviewRequestDto reviewRequestDto) {
        reviewService.editReview(reviewRequestDto, info.getUsername());

        return "redirect:/trainer/trainerSearch";
    }

    @PostMapping("/deleteReview")
    public String deleteMyReview(@AuthenticationPrincipal User info) {
        reviewService.deleteReview(info.getUsername());

        return "redirect:/trainer/trainerSearch";
    }

    @GetMapping("/review/list")
    public String getList(){
        return "content/review/review_list";
    }

}
