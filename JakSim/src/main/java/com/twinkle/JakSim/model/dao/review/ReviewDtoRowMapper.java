package com.twinkle.JakSim.model.dao.review;

import com.twinkle.JakSim.model.dto.review.ReviewDto;
import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ReviewDtoRowMapper implements RowMapper<ReviewDto> {
    @Override
    public ReviewDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ReviewDto reviewDto = new ReviewDto();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));

        reviewDto.setReviewId(rs.getInt("R_IDX"));
        reviewDto.setUserId(rs.getString("USER_ID"));
        reviewDto.setTrainerId(rs.getString("TRAINER_ID"));
        reviewDto.setReviewContent(rs.getString("R_CONTENT"));
        reviewDto.setStar(rs.getInt("R_STAR"));

        reviewDto.setReviewCreateDate(rs.getTimestamp("R_C_DT").toLocalDateTime().format(formatter));

        if(rs.getString("R_M_DT") != null) {
            reviewDto.setReviewModifyDate(rs.getTimestamp("R_M_DT").toLocalDateTime().format(formatter));
        }


        return reviewDto;
    }
}
