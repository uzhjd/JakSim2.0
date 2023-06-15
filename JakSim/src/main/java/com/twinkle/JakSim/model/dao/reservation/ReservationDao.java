package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ReservationDao {

    private JdbcTemplate jdbcTemplate;
    private String sql;

    public ReservationDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Boolean isReservate(String userId, String rCDt) {
        Boolean result = true;

        this.sql = "select * from reservation where user_id = ? and r_c_dt = ? ";

        List<ReservationDto> resResult = jdbcTemplate.query(this.sql, new resAvailableRowMapper(), userId
        , rCDt);

        if (resResult != null) {
            result = false;

            System.out.println("you have already booked");
        }

        return result;
    }

    public String register(ReservationDto reservationDto) {
        return "";
    }
}
