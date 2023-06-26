package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<ReviewRequestDto> {
    @Override
    public ReviewRequestDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();

        reviewRequestDto.setReviewId(rs.getInt("R_IDX"));
        reviewRequestDto.setUserId(rs.getString("USER_ID"));
        reviewRequestDto.setTrainerId(rs.getInt("UT_IDX"));
        reviewRequestDto.setReviewContent(rs.getString("R_CONTENT"));
        reviewRequestDto.setStar(rs.getInt("R_STAR"));
        reviewRequestDto.setReviewCreateDate(rs.getTimestamp("R_C_DT").toLocalDateTime());
        if(rs.getString("R_M_DT") != null) {
            reviewRequestDto.setReviewModifyDate(rs.getTimestamp("R_M_DT").toLocalDateTime());
        }

        reviewRequestDto.setReviewImageId(rs.getInt("RI_IDX"));
        reviewRequestDto.setReviewImagePath(rs.getString("RI_PATH"));

        return reviewRequestDto;
    }
}
