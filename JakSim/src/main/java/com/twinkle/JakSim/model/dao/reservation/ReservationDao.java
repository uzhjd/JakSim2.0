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

        this.sql = "select * from reservation where user_id = ? and r_c_dt = ? ";

        try {
            jdbcTemplate.query(this.sql, new ReservationRowMapper(), userId, rCDt);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("You have already booked");
            System.out.println(e);
            result = false;
        }

        return result;
    }

    public Boolean register(int tIdx, String userId, LocalDate rCDt) {
        int isOk = 0;
        Boolean result = true;

        this.sql = "insert into reservation " +
                "values(RESERVATION_SEQ.NEXTVAL, ?, ?, ?)";

        try {
            isOk = jdbcTemplate.update(this.sql, tIdx, userId, rCDt);

            if (isOk <= 0) {
                result = false;
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println("예약이 올바르게 되지 않았습니다.");
            return false;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
}
