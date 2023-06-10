package com.twinkle.JakSim.account;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public int insertMember(UserDO user){
        String sql = "INSERT INTO USER_INFO VALUES(?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?)";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql,
                    user.getId(), user.getPw(), user.getName(), user.getGender(), user.getTel(),
                    user.getQuestion(), user.getAnswer(), user.getBirth(), user.getRole());
        }catch(EmptyResultDataAccessException e){
            return -1;
        }catch(Exception e){
            return -1;
        }

        return result;
    }
}
