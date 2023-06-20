package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerImageDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerSignUpRowMapper implements RowMapper<TrainerInsertDto> {
    @Override
    public TrainerInsertDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        TrainerInsertDto trainerInsertDto = new TrainerInsertDto();

        trainerInsertDto.setUserId(rs.getString("USER_ID"));
        trainerInsertDto.setInsta(rs.getString("UT_INSTA"));
        trainerInsertDto.setIntroduce(rs.getString("UT_INTRO"));
        trainerInsertDto.setGym(rs.getString("UT_GYM"));
        trainerInsertDto.setExpert1(rs.getInt("UT_EXPERT_1"));
        trainerInsertDto.setExpert2(rs.getInt("UT_EXPERT_2"));

        trainerInsertDto.setPtId(rs.getInt("TP_IDX"));
        trainerInsertDto.setPtTimes(rs.getInt("TP_TIMES"));
        trainerInsertDto.setPtPrice(rs.getInt("TP_PRICE"));
        trainerInsertDto.setPtType(rs.getInt("TP_TYPE"));
        trainerInsertDto.setPtTitle(rs.getString("TP_TITLE"));
        trainerInsertDto.setPtPeriod(rs.getInt("TP_PERIOD"));

        trainerInsertDto.setCareerId(rs.getInt("TCAR_IDX"));
        trainerInsertDto.setCareerContent(rs.getString("TCAR_CONTENT"));

        trainerInsertDto.setCertId(rs.getInt("TC_IDX"));
        trainerInsertDto.setCertName(rs.getString("TC_NAME"));
        trainerInsertDto.setCertImage(rs.getString("TC_IMAGE"));

        trainerInsertDto.setImageId(rs.getInt("TI_IDX"));
        trainerInsertDto.setImagePath(rs.getString("TI_PATH"));

        return trainerInsertDto;
    }
}
















