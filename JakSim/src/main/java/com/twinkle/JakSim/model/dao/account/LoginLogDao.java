package com.twinkle.JakSim.model.dao.account;

import com.twinkle.JakSim.model.dto.account.LoginLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int create(LoginLogDto logDto) {
        String sql = "INSERT INTO LOGIN_LOG(USER_ID, L_IP) " +
                "VALUES(?, ?)";
        int result;

        try{
            result = jdbcTemplate.update(sql, logDto.getUser_id(), logDto.getIp());
        }catch (EmptyResultDataAccessException e){
            result = -1;
        }
        return result;
    }
}
