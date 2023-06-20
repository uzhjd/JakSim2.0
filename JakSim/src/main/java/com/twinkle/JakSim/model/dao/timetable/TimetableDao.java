package com.twinkle.JakSim.model.dao.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TimetableDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public Boolean isTimetable(int tIdx) {
        Boolean result = true;

        this.sql = "select * from timetable where t_idx = ?";

        try {
            jdbcTemplate.queryForObject(this.sql, new TimetableRowMapper(), tIdx);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("there isn't any timetables");
            System.out.println(e);
            result = false;
        } catch (Exception e){
            System.out.println(e);
        }

        return result;
    }
}
