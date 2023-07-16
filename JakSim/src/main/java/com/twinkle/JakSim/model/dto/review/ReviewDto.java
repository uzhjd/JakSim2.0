package com.twinkle.JakSim.model.dto.review;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private int reviewId;
    private String userId;
    private String trainerId;
    private String reviewContent;
    private int star;
    private String reviewCreateDate;
    private String reviewModifyDate;
    private String trainerName;
}
