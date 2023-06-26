package com.twinkle.JakSim.controller.review;

import com.twinkle.JakSim.model.service.review.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewRestController {

    ReviewService reviewService;
    @DeleteMapping("/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable int reviewId, @AuthenticationPrincipal User info) {
        String userId = info.getUsername();
        reviewService.deleteReview(info.getUsername());

    }
}
