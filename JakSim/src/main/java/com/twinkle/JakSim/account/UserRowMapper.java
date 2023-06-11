package com.twinkle.JakSim.account;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDO> {
    @Override
    public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDO userDO = new UserDO();

        userDO.setId(rs.getString("USER_ID"));
        userDO.setPw(rs.getString("USER_PW"));
        userDO.setName(rs.getString("USER_NAME"));
        userDO.setGender(Integer.parseInt(rs.getString("USER_GENDER")));
        userDO.setTel(rs.getString("USER_TEL"));
        userDO.setQuestion(Integer.parseInt(rs.getString("USER_QUESTION")));
        userDO.setAnswer(rs.getString("USER_ANSWER"));
        userDO.setBirth(rs.getString("USER_BIRTH"));
        userDO.setC_dt(rs.getString("USER_C_DT"));
        userDO.setRole(Integer.parseInt(rs.getString("USER_ROLE")));

        return userDO;
    }
}
