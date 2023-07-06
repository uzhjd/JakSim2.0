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

    public UserImage getRecentImage(String username){
        String sql = "SELECT * FROM USER_IMAGE WHERE USER_ID = ? ORDER BY UI_DT DESC LIMIT 1";
        UserImage image = new UserImage();

        try{
            image = jdbcTemplate.queryForObject(sql, new UserImageMapper(),username);
        }catch (EmptyResultDataAccessException e){
            image.setPath("/image/profiles/profile.jpg");
        }

        return image;
    }
}
