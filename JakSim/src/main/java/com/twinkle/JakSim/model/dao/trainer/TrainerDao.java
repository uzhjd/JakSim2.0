package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

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
