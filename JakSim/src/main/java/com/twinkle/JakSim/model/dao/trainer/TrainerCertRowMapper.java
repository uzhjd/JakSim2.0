package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerCertDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerCertRowMapper implements RowMapper<TrainerCertDto>{
    @Override
    public TrainerCertDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerCertDto trainerCertDto = new TrainerCertDto();

        trainerCertDto.setCertId(rs.getInt("TC_IDX"));
        trainerCertDto.setUserId(rs.getString("USER_ID"));
        trainerCertDto.setCertName(rs.getString("TC_NAME"));
        trainerCertDto.setCertImage(rs.getString("TC_IMAGE"));

        return trainerCertDto;
    }
}
