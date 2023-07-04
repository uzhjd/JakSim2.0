package com.twinkle.JakSim.model.dao.account;


import com.twinkle.JakSim.model.dto.account.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto userDto = new UserDto();

        userDto.setId(rs.getString("USER_ID"));
        userDto.setPw(rs.getString("USER_PW"));
        userDto.setName(rs.getString("USER_NAME"));
        userDto.setGender(Integer.parseInt(rs.getString("USER_GENDER")));
        userDto.setTel(rs.getString("USER_TEL"));
        userDto.setEmail(rs.getString("USER_EMAIL"));
        userDto.setQuestion(Integer.parseInt(rs.getString("USER_QUESTION")));
        userDto.setAnswer(rs.getString("USER_ANSWER"));
        userDto.setBirth(rs.getString("USER_BIRTH"));
        userDto.setC_dt(rs.getString("USER_C_DT"));
        userDto.setRole(Integer.parseInt(rs.getString("USER_ROLE")));

        return userDto;
    }
}
