package com.twinkle.JakSim.model.dto.review;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private int reviewId;
    private String userId;
    private int trainerId;
    private String reviewContent;
    private int star;
    private LocalDate reviewCreateDate;
    private LocalDate reviewModifyDate;
}
