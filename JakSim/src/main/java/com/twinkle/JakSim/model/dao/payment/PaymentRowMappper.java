package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMappper implements RowMapper<PaymentDto> {

    public PaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentDto paymentDto = new PaymentDto(rs.getInt("P_IDX"),
                                                rs.getString("USER_ID"),
                                                rs.getInt("TP_IDX"),
                                                rs.getDate("P_C_DT").toLocalDate(),
                                                rs.getInt("P_REFOUND"),
                                                rs.getDate("P_M_DT").toLocalDate(),
                                                rs.getInt("P_PT_CNT"));

        return paymentDto;
    }
}
