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

    /**
     * TRAINER_ID 누락
     * @param review
     * @param userId
     * @param trainerIdx
     */

    // 리뷰 등록
    public void insertReview(ReviewRequestDto review, String userId, int trainerIdx) {
        this.sql = "INSERT INTO REVIEW VALUES(NULL, ?, ?, ?, ?, current_timestamp, NULL)";

        jdbcTemplate.update(this.sql, userId, trainerIdx,
                            review.getReviewContent(), review.getStar());

    }

    // 트레이너 리뷰 미리보기 (최신순)
    public List<ReviewRequestDto> getTrainerReview(int trainerId) {
        this.sql = "SELECT * " +
                "FROM REVIEW " +
                "WHERE UT_IDX = ? "+
                " ORDER BY R_IDX DESC" +
                " LIMIT 3";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), trainerId);
    }

    // 트레이너 리뷰 전체보기
    public List<ReviewRequestDto> getTrainerReviewAll(int page, int pageSize, int filter, int trainerId) {
        //1. 최신순 (기본)
        //2. 별점 높은순
        //3. 별점 낮은순

        int offset = (page - 1) * pageSize;

        if(filter == 0) {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE UT_IDX = ? "+
                    " ORDER BY R_IDX DESC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql, new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());

        }
        else if(filter == 1) {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE UT_IDX = ? "+
                    " ORDER BY R_STAR DESC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql,  new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());
        }
        else {
            String sql = "SELECT *" +
                    "FROM REVIEW " +
                    "WHERE UT_IDX = ? "+
                    " ORDER BY R_STAR ASC" +
                    " LIMIT ?, ?";

            return jdbcTemplate.query(sql,  new Object[]{trainerId, offset, pageSize}, new ReviewRowMapper());
        }

    }

    // 리뷰 별점 및 전체수 count
    public ReviewRequestDto getStarAvgAndCnt(int utIdx) {
        this.sql = "SELECT *, COUNT(*) AS REVIEW_CNT, ROUND(AVG(R_STAR), 1) AS AVG_R_STAR " +
                "FROM REVIEW WHERE UT_IDX = ?";

        return jdbcTemplate.queryForObject(this.sql, new ReviewRowMapper2(), utIdx);

    }

    // 리뷰 정보 가져오기 (리뷰 인덱스별)
    public List<ReviewRequestDto> getMyReview(String userId, int reviewIdx) {
        this.sql = "SELECT * FROM REVIEW " +
                "WHERE USER_ID = ? AND R_IDX = ?";

        return jdbcTemplate.query(this.sql, new ReviewRowMapper(), userId, reviewIdx);
    }

    // 나의 리뷰 전체 가져오기 (마이페이지용)
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

    // 리뷰 수정
    public void editReview(ReviewRequestDto review, String userId) {
        this.sql = "UPDATE REVIEW SET R_CONTENT = ?, R_STAR = ?, R_M_DT = current_timestamp " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, review.getReviewContent(),
                review.getStar(), userId);

    }

    // 리뷰 삭제
    public void deleteReview(String userId) {
        this.sql = "DELETE FROM REVIEW WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, userId);

    }
}
