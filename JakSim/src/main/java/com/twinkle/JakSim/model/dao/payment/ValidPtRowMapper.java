package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidPtRowMapper implements RowMapper<ValidPtDto> {

    public ValidPtDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ValidPtDto validPt = ValidPtDto.builder()
                                        .trainerId(rs.getString("USER_ID"))
                                        .pIdx(rs.getInt("P_IDX"))
                                        .pPtCnt(rs.getInt("P_PT_CNT"))
                                        .build();

        return validPt;
    }
}
