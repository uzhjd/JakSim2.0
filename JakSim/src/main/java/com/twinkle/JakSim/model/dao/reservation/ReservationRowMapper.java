package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.response.IsReservationDto;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<ReservationResponse> {
    public ReservationResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservationResponse reservationResponse = ReservationResponse.builder()
                                                        .rIdx(rs.getInt("R_IDX"))
                                                        .tStartT(rs.getTime("T_START_T").toLocalTime())
                                                        .tEndT(rs.getTime("T_END_T").toLocalTime())
                                                        .tPeople(rs.getInt("T_PEOPLE"))
                                                        .tType(rs.getInt("T_TYPE"))
                                                        .build();

        return reservationResponse;
    }
}