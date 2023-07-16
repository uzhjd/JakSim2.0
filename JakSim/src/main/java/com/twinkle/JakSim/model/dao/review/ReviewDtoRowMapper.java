package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dto.review.ReviewDto;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDtoRowMapper implements RowMapper<ReviewDto> {
    @Override
    public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setReviewId(rs.getInt("R_IDX"));
        reviewDto.setUserId(rs.getString("USER_ID"));
        reviewDto.setTrainerId(rs.getString("TRAINER_ID"));
        reviewDto.setReviewContent(rs.getString("R_CONTENT"));
        reviewDto.setStar(rs.getInt("R_STAR"));
        reviewDto.setReviewCreateDate(rs.getString("R_C_DT"));
        if(rs.getTimestamp("R_M_DT") != null)
            reviewDto.setReviewModifyDate(rs.getString("R_M_DT"));
        reviewDto.setTrainerName(rs.getString("USER_NAME"));

        return reviewDto;
    }
}
