package com.twinkle.JakSim.model.dao.timetable;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimetableRowMapper implements RowMapper<TimetableDto> {
    public TimetableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimetableDto timetableDto = new TimetableDto();

        timetableDto.setTIdx(rs.getInt("T_IDX"));
        timetableDto.setUtIdx(rs.getInt("UT_IDX"));
        timetableDto.setTStartT(rs.getString("T_START_T"));
        timetableDto.setTEndT(rs.getString("T_END_T"));
        timetableDto.setTPeople(rs.getInt("T_PEOPLE"));
        timetableDto.setTType(rs.getInt("T_TYPE"));

        return timetableDto;
    }
}
