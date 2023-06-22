package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.response.IsReservationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IsReservationRowMapper implements RowMapper<IsReservationDto> {
    public IsReservationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        IsReservationDto isReservationDto = new IsReservationDto(rs.getInt("rIdx"),
                                                                    rs.getInt("T_IDX"),
                                                                    rs.getString(("USER_ID")),
                                                                    rs.getDate("R_C_DT").toLocalDate()
        );

        return isReservationDto;
    }
}
