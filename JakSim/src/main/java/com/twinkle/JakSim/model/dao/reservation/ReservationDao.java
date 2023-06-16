package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDao {

    private JdbcTemplate jdbcTemplate;
    private String sql;

    public ReservationDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Boolean isReservate(String userId, LocalDate rCDt) {
        Boolean result = true;

//        this.sql = "select p_idx from reservation where user_id = ? and r_c_dt = ? ";
        this.sql = "select * from reservation where user_id = ? and r_c_dt = ? ";

        try {
//            jdbcTemplate.query(this.sql, new BookedRowMapper(), userId, rCDt);
            jdbcTemplate.query(this.sql, new ReservationRowMapper(), userId, rCDt);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("You have already booked");
            System.out.println(e);
            result = false;
        }

        return result;
    }

    public Boolean register(ReservationDto reservationDto) {
        Boolean result = true;

        this.sql = "insert into reservation " +
                "values(RESERVATION_SEQ.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try {
            jdbcTemplate.update(this.sql, );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("예약이 올바르게 되지 않았습니다.");
            return false;
        }



        return result;
    }
}
