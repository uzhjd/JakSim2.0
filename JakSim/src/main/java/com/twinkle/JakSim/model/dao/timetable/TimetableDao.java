package com.twinkle.JakSim.model.dao.timetable;

import com.twinkle.JakSim.model.dao.reservation.resAvailableRowMapper;
import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import com.twinkle.JakSim.model.dto.timetable.TimetableDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TimetableDao {

    private JdbcTemplate jdbcTemplate;
    private String sql;

    public TimetableDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Boolean isTimetable(int tIdx) {
        Boolean result = true;

        this.sql = "select * from timetable where t_idx = ?";

        List<TimetableDto> resResult = jdbcTemplate.query(this.sql, new resAvailableRowMapper(), tIdx);

        if (resResult == null) {
            result = false;

            System.out.println("we don't have this timetatble");
        }

        return result;
    }
}
