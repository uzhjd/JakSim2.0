package com.twinkle.JakSim.model.dao.trainer;


import com.twinkle.JakSim.model.dao.reservation.ReservationRowMapper;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

public class TrainerDao {

    private JdbcTemplate jdbcTemplate;
    private String sql;

    public TrainerDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public TrainerDto findAllValidPt(int utIdx) {
        TrainerDto trainerInfo = null;

        this.sql = "select * from trainer_details where ut_idx = ?";

        try {
            trainerInfo = jdbcTemplate.queryForObject(this.sql, new TrainerRowMapper(), utIdx);
        } catch(Exception e) {
            System.out.println(e);
        }

        return trainerInfo;
    }

}
