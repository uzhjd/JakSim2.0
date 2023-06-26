package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerDetailRowMapper implements RowMapper<TrainerDetailDto> {

    public TrainerDetailDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDetailDto trainerDetailDto = new TrainerDetailDto(rs.getInt("TRAINER_IDX"),
                                                                    rs.getString("USER_ID"),
                                                                    rs.getString("USER_NAME"),
                                                                    rs.getInt("GENDER"),
                                                                    rs.getString("INSTA"),
                                                                    rs.getString("INTRODUCE"),
                                                                    rs.getString("GYM"),
                                                                    rs.getInt("EXPERT1"),
                                                                    rs.getInt("EXPERT2"));

        return trainerDetailDto;
    }
}
