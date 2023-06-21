package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<ReservationDto> {
    public ReservationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservationDto reservationDto = new ReservationDto(rs.getString("USER_ID"),
                                                            rs.getInt("P_IDX"),
                                                            rs.getInt("T_IDX"),
                                                            rs.getDate("T_DATE").toLocalDate(),
                                                            rs.getDate("R_C_DT").toLocalDate()
        );

        return reservationDto;
    }
}
