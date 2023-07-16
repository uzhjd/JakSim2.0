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

    public void insertReview(ReviewRequestDto review, String userId, int trainerIdx) {
        this.sql = "INSERT INTO REVIEW VALUES(NULL, ?, ?, ?, ?, current_date, NULL)";

        jdbcTemplate.update(this.sql, userId, trainerIdx,
                            review.getReviewContent(), review.getStar());

    }

    public List<ReviewRequestDto> getTrainerReview(String trainerId) {
        this.sql = "SELECT * FROM REVIEW " +
                "WHERE TRAINER_ID = ?";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), trainerId);
    }

    public List<ReviewRequestDto> getMyReview(String userId) {
        this.sql = "SELECT * FROM REVIEW " +
                "WHERE USER_ID = ?";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), userId);
    }


    public Optional<List<ReviewRequestDto>> getMyReviewForMypage(String userId) {
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
