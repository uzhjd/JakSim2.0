package com.twinkle.JakSim.model.dao.timetable;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimetableRowMapper implements RowMapper<TimetableDto> {
    public TimetableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimetableDto timetableDto = TimetableDto.builder()
                                                .tIdx(rs.getInt("T_IDX"))
                                                .userId(rs.getInt("USER_ID"))
                                                .tDate(rs.getDate("T_DATE").toLocalDate())
                                                .tStartT(rs.getTime("T_START_T").toLocalTime())
                                                .tEndT(rs.getTime("T_END_T").toLocalTime())
                                                .tPeople(rs.getInt("T_PEOPLE"))
                                                .tType(rs.getInt("T_TYPE"))
                                                .build();

        return timetableDto;
    }
}
