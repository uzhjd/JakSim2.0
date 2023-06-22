package com.twinkle.JakSim.model.dao.reservation;

import com.twinkle.JakSim.model.dto.reservation.response.IsReservationDto;
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
            jdbcTemplate.query(this.sql, new IsReservationRowMapper(), userId, tDate);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("You have already booked");
            System.out.println(e);
            result = false;
        }

        return result;
    }

    public Boolean register(int tIdx, String userId) {
        int isOk = 0;
        Boolean result = true;

        this.sql = "insert into reservation " +
                "values(RESERVATION_SEQ.NEXTVAL, ?, ?, to_date(sysdate,'YYYY/MM/DD'))";

        try {
            isOk = jdbcTemplate.update(this.sql, tIdx, userId);

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


    public Boolean delete(int rIdx) {
        this.sql = "delete from reservation where t_idx = ?";

        try {
            jdbcTemplate.update(this.sql, rIdx);
        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        this.sql = "select * from reservation where t_idx = ?";

        try {
            jdbcTemplate.queryForObject(this.sql, new IsReservationRowMapper(), rIdx);
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}