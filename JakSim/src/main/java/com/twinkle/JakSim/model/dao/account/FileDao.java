package com.twinkle.JakSim.model.dao.account;

import com.twinkle.JakSim.model.dto.account.UserImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int create(UserImage data) {
        String sql = "INSERT INTO USER_IMAGE(USER_ID, UI_PATH) " +
                "VALUES(? ,?)";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql, data.getUser_id(), data.getPath());
        }catch (EmptyResultDataAccessException ignored){

        }
        return result;
    }
}
