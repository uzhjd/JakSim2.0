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
        String sql = "INSERT INTO USER_INFO(user_id, user_pw, user_name, user_gender, user_tel, user_email, user_question, user_answer, user_birth, user_c_dt, user_role) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?)";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql,
                    user.getId(), user.getPw(), user.getName(), user.getGender(), user.getTel(), user.getEmail(),
                    user.getQuestion(), user.getAnswer(), user.getBirth(), user.getRole());
        }catch(EmptyResultDataAccessException e){
            return -1;
        }

        return result;
    }

    public UserDto findByUserId(String userId){
        String sql = "SELECT * FROM USER_INFO WHERE USER_ID = ?";
        UserDto userDto = null;
        try{
            userDto = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        } catch(EmptyResultDataAccessException e){
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

    public int updatePassword(String user_id, String pw) {
        String sql = "update user_info " +
                "set user_pw = ? " +
                "where user_id = ?";
        int result = -1;

        try{
            result = jdbcTemplate.update(sql, pw, user_id);
        }catch (Exception e){
            System.out.println("모시깽이 에러");
        }

        return result;
    }

    public int deleteUser(String id) {
        String sql = "DELETE FROM USER_INFO WHERE USER_ID = ?";
        int result = -1;

        try{
            result = jdbcTemplate.update(sql, id);
        }catch (Exception e){
            System.out.println("회원삭제 실패");
        }
        return result;
    }

    public UserDto findByEmail(String email) {
        String sql = "SELECT * FROM USER_INFO WHERE USER_EMAIL = ?";
        UserDto user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, new UserRowMapper() ,email);
        }catch (EmptyResultDataAccessException e){
            System.out.println("데이터가 없다네예");
        }
        return user;
    }

    public int updateEmail(String email, String username) {
        String sql = "UPDATE USER_INFO " +
                "SET USER_EMAIL = ? " +
                "WHERE USER_ID = ?";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql, email, username);
        }catch (Exception e){
            System.out.println("모시깽이 에러");
        }

        return result;
    }

    public int updateName(String name, String username) {
        String sql = "UPDATE USER_INFO " +
                "SET USER_NAME = ? " +
                "WHERE USER_ID = ?";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql, name, username);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int updateTel(String tel, String username) {
        String sql = "UPDATE USER_INFO " +
                "SET USER_TEL = ? " +
                "WHERE USER_ID = ?";
        int result = -1;
        try{
            result = jdbcTemplate.update(sql, tel, username);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
