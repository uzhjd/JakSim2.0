package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.dto.reservation.response.ResIsAvailableDto;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMappper implements RowMapper<PaymentDto> {

    public PaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setPIdx(rs.getInt("P_IDX"));
        paymentDto.setUserId(rs.getString("USER_ID"));
        paymentDto.setTpIdx(rs.getInt("TP_IDX"));
        paymentDto.setPCDt(rs.getDate("P_C_DT").toLocalDate());
        paymentDto.setPRefund(rs.getInt("P_REFOUND"));
        paymentDto.setPMDt(rs.getDate("P_M_DT").toLocalDate());
        paymentDto.setPPtCnt(rs.getInt("P_PT_CNT"));

        return paymentDto;
    }
}
