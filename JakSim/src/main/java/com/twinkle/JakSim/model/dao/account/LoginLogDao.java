package com.twinkle.JakSim.model.dao.account;

import com.twinkle.JakSim.model.dto.account.LoginLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public LoginLogDto findByUsernameRecent(String userId) {
        String sql = "SELECT * FROM LOGIN_LOG WHERE USER_ID = ? ORDER BY L_DT desc LIMIT 1";

        LoginLogDto logDto = new LoginLogDto();

        try{
            logDto = jdbcTemplate.queryForObject(sql, new LoginLogMapper(), userId);
        }catch (EmptyResultDataAccessException e){
            System.out.println("로그인 기록이 없다는데?");
        }

        return logDto;
    }

    public List<LoginLogDto> findByUsername(String username) {
        String sql = "SELECT * FROM LOGIN_LOG WHERE USER_ID = ? ORDER BY L_DT desc";
        List<LoginLogDto> resultList = new ArrayList<>();

        try{
            resultList = jdbcTemplate.query(sql, new LoginLogMapper(), username);
        }catch (EmptyResultDataAccessException e){
            System.out.println("로그인 기록이 없다는데?");
        }

        return resultList;
    }
}
