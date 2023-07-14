package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public void insertReview(ReviewRequestDto review) {
        this.sql = "INSERT INTO REVIEW VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(this.sql, review.getUserId(), review.getTrainerId(),
                            review.getReviewContent(), review.getStar(),
                            review.getReviewCreateDate(), review.getReviewModifyDate());

        this.sql = "INSERT INTO REVIEW_IMAGE VALUES(NULL, ?, ?)";

        jdbcTemplate.update(this.sql, review.getReviewId(), review.getReviewImagePath());

    }

    public List<ReviewRequestDto> getTrainerReview(int utIdx) {
        this.sql = "SELECT * FROM REVIEW R JOIN REVIEW_IMAGE RI " +
                "ON R.R_IDX = RI.R_IDX " +
                "WHERE UT_IDX = ?";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), utIdx);
    }

    public Optional<List<ReviewRequestDto>> getMyReview(String userId) {
        this.sql = "SELECT * FROM REVIEW R " +
                "WHERE USER_ID = ? " +
                "ORDER BY R_IDX DESC " +
                "LIMIT 3";
        List<ReviewRequestDto> reviewList = null;
        try {
            reviewList = jdbcTemplate.query(this.sql, new ReviewRowMapper(), userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(reviewList);
    }


    public void editReview(ReviewRequestDto review, String userId) {
        this.sql = "UPDATE REVIEW SET R_CONTENT = ?, R_STAR = ?, R_M_DT = current_date " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, review.getReviewContent(),
                review.getStar(), userId);

    }

    public void deleteReview(String userId) {
        this.sql = "DELETE FROM REVIEW WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, userId);

    }

}
