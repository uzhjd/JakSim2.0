package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidPtRowMapper implements RowMapper<TrainerDto> {

    public TrainerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDto validPt = new TrainerDto();

        validPt.setId(rs.getInt("UT_IDX"));
        validPt.setId(rs.getInt("P_PT_CNT"));

        return validPt;
    }
}
