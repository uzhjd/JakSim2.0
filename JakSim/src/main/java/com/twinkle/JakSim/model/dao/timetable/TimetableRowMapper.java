package com.twinkle.JakSim.model.dao.timetable;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimetableRowMapper implements RowMapper<TimetableDto> {
    public TimetableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimetableDto timetableDto = new TimetableDto(rs.getInt("T_IDX"),
                                                        rs.getInt("USER_ID"),
                                                        rs.getDate("T_DATE").toLocalDate(),
                                                        rs.getTime("T_START_T").toLocalTime(),
                                                        rs.getTime("T_END_T").toLocalTime(),
                                                        rs.getInt("T_PEOPLE"),
                                                        rs.getInt("T_TYPE"));

        return timetableDto;
    }
}
