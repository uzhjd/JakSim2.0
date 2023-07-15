package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerPageDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerPage2RowMapper implements RowMapper<TrainerPageDto> {
    @Override
    public TrainerPageDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        TrainerPageDto trainerPageDto = new TrainerPageDto();

        trainerPageDto.setPtTimes(rs.getInt("TP_TIMES"));   //
        trainerPageDto.setPtPrice(rs.getInt("TP_PRICE"));   //
        trainerPageDto.setPtTitle(rs.getString("TP_TITLE"));    //

        trainerPageDto.setCareerContent(rs.getString("TCAR_CONTENT"));  //

        trainerPageDto.setCertName(rs.getString("TC_NAME"));    //

        trainerPageDto.setImagePath(rs.getString("TI_PATH"));   //

        return trainerPageDto;
    }
}
