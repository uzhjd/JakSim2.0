package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dao.trainer.PtUserRowMapper;
import com.twinkle.JakSim.model.dto.review.ReviewDto;
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
        this.sql = "INSERT INTO REVIEW VALUES(NULL, ?, ?, ?, ?, current_timestamp, NULL)";

        jdbcTemplate.update(this.sql, userId, trainerIdx,
                            review.getReviewContent(), review.getStar());

    }

    public List<ReviewRequestDto> getTrainerReview(String trainerId) {
        this.sql = "SELECT *" +
                "FROM REVIEW " +
                "WHERE TRAINER_ID = ? "+
                " ORDER BY R_IDX DESC" +
                " LIMIT 3";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), trainerId);
    }

    public List<ReviewRequestDto> getTrainerReviewAll(int page, int pageSize, int filter, String trainerId) {

        int offset = (page - 1) * pageSize;

        if(filter == 0) {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE TRAINER_ID = ? "+
                    " ORDER BY R_IDX DESC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql, new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());

        }
        else if(filter == 1) {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE TRAINER_ID = ? "+
                    " ORDER BY R_STAR DESC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql,  new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());
        }
        else {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE TRAINER_ID = ? "+
                    " ORDER BY R_STAR ASC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql,  new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());
        }

    }

    public ReviewRequestDto getStarAvgAndCnt(String trainerId) {
        this.sql = "SELECT *, COUNT(*) AS REVIEW_CNT, ROUND(AVG(R_STAR), 1) AS AVG_R_STAR " +
                "FROM REVIEW WHERE TRAINER_ID = ?";

        return jdbcTemplate.queryForObject(this.sql, new ReviewRowMapper2(), trainerId);

    }

    public List<ReviewRequestDto> getMyReview(String userId, int reviewIdx) {
        this.sql = "SELECT * FROM REVIEW " +
                "WHERE USER_ID = ? AND R_IDX = ?";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), userId, reviewIdx);
    }

    public Optional<List<ReviewRequestDto>> getMyReviewForMyPage(String userId) {
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
        this.sql = "UPDATE REVIEW SET R_CONTENT = ?, R_STAR = ?, R_M_DT = current_timestamp " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, review.getReviewContent(),
                review.getStar(), userId);

    }

    public void deleteReview(String userId) {
        this.sql = "DELETE FROM REVIEW WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, userId);

    }

    public List<ReviewDto> getReviewItems(String username, int page) {
        int offset = (page-1) * 10;
        String sql = "SELECT R.R_IDX, R.USER_ID, R.TRAINER_ID, R.UT_IDX, R.R_CONTENT, R.R_STAR, R.R_C_DT, R.R_M_DT, U.USER_NAME " +
                "FROM REVIEW R, TRAINER_DETAILS T, USER_INFO U " +
                "WHERE R.TRAINER_ID = T.USER_ID AND T.USER_ID = U.USER_ID " +
                "AND R.USER_ID = ?";
        List<ReviewDto> reviewList = new ArrayList<>();

        try{
            reviewList = jdbcTemplate.query(sql, new ReviewDtoRowMapper(), username);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return reviewList;
    }
}
