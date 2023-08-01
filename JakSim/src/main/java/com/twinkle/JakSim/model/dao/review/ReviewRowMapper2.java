package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ReviewRowMapper2 implements RowMapper<ReviewRequestDto> {
    @Override
    public ReviewRequestDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));

        reviewRequestDto.setReviewId(rs.getInt("R_IDX"));
        reviewRequestDto.setUserId(rs.getString("USER_ID"));
        reviewRequestDto.setTrainerId(rs.getInt("UT_IDX"));
        reviewRequestDto.setReviewContent(rs.getString("R_CONTENT"));
        reviewRequestDto.setStar(rs.getInt("R_STAR"));

        //reviewRequestDto.setReviewCreateDate(rs.getTimestamp("R_C_DT").toLocalDateTime().format(formatter));

        if(rs.getString("R_C_DT") != null) {
            reviewRequestDto.setReviewCreateDate(rs.getTimestamp("R_C_DT").toLocalDateTime().format(formatter));
        }
        if(rs.getString("R_M_DT") != null) {
            reviewRequestDto.setReviewModifyDate(rs.getTimestamp("R_M_DT").toLocalDateTime().format(formatter));
        }

        try {
            reviewRequestDto.setAvgRstar(rs.getDouble("AVG_R_STAR"));
        } catch (SQLException ex) {
            reviewRequestDto.setAvgRstar(0.0);
        }

        try {
            reviewRequestDto.setReviewCnt(rs.getInt("REVIEW_CNT"));
        } catch (SQLException ex) {
            reviewRequestDto.setReviewCnt(0);
        }

        return reviewRequestDto;
    }
}
