package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.dto.reservation.response.ResIsAvailableDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookedRowMapper implements RowMapper<ResIsAvailableDto> {
    public ResIsAvailableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResIsAvailableDto resIsAvailableDto = new ResIsAvailableDto();

        resIsAvailableDto.setRIdx(rs.getInt("P_IDX"));

        return resIsAvailableDto;
    }
}
