package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerDetailRowMapper implements RowMapper<TrainerDetailResponse> {

    public TrainerDetailResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDetailResponse trainerDetailResponse = new TrainerDetailResponse(rs.getString("USER_ID"),
                                                                    rs.getString("USER_NAME"),
                                                                    rs.getInt("GENDER"),
                                                                    rs.getString("INSTA"),
                                                                    rs.getString("INTRO"),
                                                                    rs.getString("GYM"),
                                                                    rs.getInt("EXPERT1"),
                                                                    rs.getInt("EXPERT2"));

        return trainerDetailResponse;
    }
}
