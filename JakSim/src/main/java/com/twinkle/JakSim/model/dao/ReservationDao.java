package com.twinkle.JakSim.model.dao;

import com.twinkle.JakSim.model.dto.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ReservationDao {

    private JdbcTemplate jdbcTemplate;
    private String sql;

    public ReservationDao(DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Boolean resAvailable() throws Exception {
        Boolean result = true;

        // 해당일에 내 예약이 없나? 없어야 예약을 따로 할 수 있다.
        this.sql = "select * from reservation where user_id = ? and r_c_dt = ? ";

        try {
            jdbcTemplate.query(this.sql, new resAvailableRowMapper());
        } catch (EmptyResultDataAccessException e) {
            if (e != null) {
                throw new Exception("you have already booked");
                System.out.println("이미 예약이 존재합니다.");

                result = false;
            }
        } catch (Exception e) {
            // Handle other exceptions
        }

        return result;
    }
    public String register(ReservationDto reservationDto){
        return "";
    }
}
