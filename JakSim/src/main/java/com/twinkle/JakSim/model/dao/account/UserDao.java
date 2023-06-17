package com.twinkle.JakSim.model.dao.account;

import com.twinkle.JakSim.model.dto.account.UserDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertMember(UserDto user){
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

    public UserDto findByUserId(String userId){
        String sql = "SELECT * FROM USER_INFO WHERE USER_ID = ?";
        UserDto userDto = null;
        try{
            userDto = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        }catch(EmptyResultDataAccessException e){
            System.out.println("데이터가 없다는디?");
        }

        return userDto;
    }

    public UserDto findByTel(String userTel){
        String sql = "SELECT * FROM USER_INFO WHERE USER_TEL = ?";
        UserDto userDto = null;

        try{
            userDto = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userTel);
        }catch (EmptyResultDataAccessException e){
            System.out.println("데이터 없음");
        }

        return userDto;
    }

}
