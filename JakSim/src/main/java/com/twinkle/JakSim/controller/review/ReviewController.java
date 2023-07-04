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

    @GetMapping("/editReview")
    public String editReview(Model model, @AuthenticationPrincipal User info,
                             ReviewRequestDto reviewRequestDto) {
        model.addAttribute("head_title", "리뷰 수정");
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReivew(info.getUsername()));

        return "content/review/editReview";
    }

    @PostMapping("/editMyReview")
    public String editMyReview(Model model, @AuthenticationPrincipal User info,
                               ReviewRequestDto reviewRequestDto) {
        model.addAttribute("userId", info);
        model.addAttribute("review", reviewService.showMyReivew(info.getUsername()));
        reviewService.editReview(reviewRequestDto, info.getUsername());

        return "redirect:/editReview";
    }

    @PostMapping("/deleteReview")
    public String deleteMyReview(Model model, @AuthenticationPrincipal User info,
                               ReviewRequestDto reviewRequestDto) {
        //model.addAttribute("userId", info);
        reviewService.deleteReview(info.getUsername());

        return "redirect:/trainer/{trainerId}";
    }

//    @PostMapping("/trainer/ptTimetableUpdate")
//    public String timetableDelete(@RequestParam("tIdx") int tIdx){
//        trainerService.deleteTimetable(tIdx);
//
//        return "redirect:/trainer/trainerControl";
//    }


}
