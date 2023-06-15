package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class resAvailableRowMapper implements RowMapper<ReservationDto> {
    public ReservationDto mapRow(ResultSet rs, int fowNum) throws SQLException {
        ReservationDto reservationDto = new ReservationDto();




        return reservationDto;
    }
}
