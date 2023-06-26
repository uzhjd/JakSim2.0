package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerSearchDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerSearchRowMapper implements RowMapper<TrainerSearchDto> {
    @Override
    public TrainerSearchDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        TrainerSearchDto trainerSearchDto = new TrainerSearchDto();

        trainerSearchDto.setTrainerId(rs.getInt("UT_IDX"));
        trainerSearchDto.setUserName(rs.getString("USER_NAME"));
        trainerSearchDto.setInsta(rs.getString("UT_INSTA"));
        trainerSearchDto.setIntroduce(rs.getString("UT_INTRO"));
        trainerSearchDto.setGym(rs.getString("UT_GYM"));
        trainerSearchDto.setExpert1(rs.getInt("UT_EXPERT_1"));
        trainerSearchDto.setExpert2(rs.getInt("UT_EXPERT_2"));
        trainerSearchDto.setPtTimes(rs.getInt("TP_TIMES"));
        trainerSearchDto.setPtPrice(rs.getInt("TP_PRICE"));
        trainerSearchDto.setPtTitle(rs.getString("TP_TITLE"));
        trainerSearchDto.setCareerContent(rs.getString("TCAR_CONTENT"));
        trainerSearchDto.setCertName(rs.getString("TC_NAME"));
        trainerSearchDto.setCertImage(rs.getString("TC_IMAGE"));
        trainerSearchDto.setImagePath(rs.getString("TI_PATH"));

        return trainerSearchDto;
    }
}
