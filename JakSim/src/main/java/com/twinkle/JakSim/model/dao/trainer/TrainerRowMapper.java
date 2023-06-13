package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class TrainerRowMapper implements RowMapper<TrainerDto>{

    @Override
    public TrainerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDto trainerDo = new TrainerDto();

        trainerDo.setId(rs.getInt("UT_IDX"));
        trainerDo.setIntroduce(rs.getString("UT_INTRO"));
        trainerDo.setInsta(rs.getString("UT_INSTA"));
        trainerDo.setGym(rs.getString("UT_GYM"));
        trainerDo.setUserId(rs.getString("USER_ID"));
        trainerDo.setExpert1(rs.getInt("UT_EXPERT_1"));
        trainerDo.setExpert2(rs.getInt("UT_EXPERT_2"));

        return trainerDo;
    }
}
