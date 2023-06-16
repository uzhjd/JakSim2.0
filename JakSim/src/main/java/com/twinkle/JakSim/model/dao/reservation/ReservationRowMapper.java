package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<ReservationDto> {
    public ReservationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setUserId(rs.getString("USER_ID"));
        reservationDto.setUtIdx(rs.getInt("UT_IDX"));
        reservationDto.setPIdx(rs.getInt("P_IDX"));
        reservationDto.setTIdx(rs.getInt("T_IDX"));
        reservationDto.setRCDt(rs.getDate("R_C_DT").toLocalDate());

        return reservationDto;
    }
}
