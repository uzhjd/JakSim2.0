package com.twinkle.JakSim.model.dao.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ReservationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public Boolean isReservate(String userId, LocalDate tDate) {
        Boolean result = true;

        this.sql = "select * from reservation where user_id = ? and r_c_dt = ? ";

        try {
            jdbcTemplate.queryForObject(this.sql, new IsReservationRowMapper(), userId, tDate);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("You have already booked");
            System.out.println(e);
            result = false;
        }

        return result;
    }

    public Boolean register(String userId, int tIdx, int pIdx) {
        Boolean result = true;

        this.sql = "insert into reservation " +
                "values(?, ?, ?)";

        try {
            jdbcTemplate.update(this.sql, tIdx, userId, pIdx);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("예약이 올바르게 되지 않았습니다.");

            return false;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public Boolean delete(int rIdx) {
        this.sql = "delete from reservation where t_idx = ?";

        try {
            int rowsAffected = jdbcTemplate.update(this.sql, rIdx);

            if (rowsAffected != 0) {
                return true;
            }
            throw new RuntimeException("No rows were affected by the update operation");
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}