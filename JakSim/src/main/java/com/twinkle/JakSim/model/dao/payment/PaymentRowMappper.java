package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMappper implements RowMapper<PaymentDto> {

    public PaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentDto paymentDto = PaymentDto.builder()
                .pIdx(rs.getInt("P_IDX"))
                .pPtCnt(rs.getInt("P_PT_CNT"))
                .build();

        return paymentDto;
    }
}
