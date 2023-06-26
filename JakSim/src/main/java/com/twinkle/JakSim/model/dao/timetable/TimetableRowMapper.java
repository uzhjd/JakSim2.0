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
        timetableDto.setUserId(rs.getString("USER_ID"));
        timetableDto.setTStartT(rs.getTime("T_START_T").toLocalTime());
        timetableDto.setTEndT(rs.getTime("T_END_T").toLocalTime());
        timetableDto.setTPeople(rs.getInt("T_PEOPLE"));
        timetableDto.setTType(rs.getInt("T_TYPE"));

        return timetableDto;
    }
}
