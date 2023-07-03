package com.twinkle.JakSim.model.dao.timetable;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public List<TimetableDto> findAllTimetable(String userId) {
        List<TimetableDto> timetableDtoList = new ArrayList<>();

        this.sql = "select * from timetable as tt inner join trainer_details as td on tt.ut_idx = td.ut_idx" +
                " where user_id = ?";

        try {
            timetableDtoList = jdbcTemplate.query(this.sql, new TimetableRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("there isn't any timetables");
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }

        return timetableDtoList;
    }
}
