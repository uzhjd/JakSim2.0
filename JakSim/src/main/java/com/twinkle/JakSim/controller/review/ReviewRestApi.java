package com.twinkle.JakSim.controller.review;

import com.twinkle.JakSim.model.dto.review.ReviewDto;
import com.twinkle.JakSim.model.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewRestApi {
    private final ReviewService reviewService;
    @GetMapping("/review/api/{page}")
    public List<ReviewDto> getReviewItems(@PathVariable("page") int page, @AuthenticationPrincipal User user){
        return reviewService.getReviewItems(user.getUsername(), page);
    }
}
