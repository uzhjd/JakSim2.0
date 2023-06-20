package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class TrainerRowMapper implements RowMapper<TrainerDto>{

    @Override
    public TrainerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDto trainerDto = new TrainerDto();

        trainerDto.setTrainerId(rs.getInt("UT_IDX"));
        trainerDto.setUserId(rs.getString("USER_ID"));
        trainerDto.setIntroduce(rs.getString("UT_INTRO"));
        trainerDto.setInsta(rs.getString("UT_INSTA"));
        trainerDto.setGym(rs.getString("UT_GYM"));
        trainerDto.setExpert1(rs.getInt("UT_EXPERT_1"));
        trainerDto.setExpert2(rs.getInt("UT_EXPERT_2"));

        return trainerDto;
    }
}
