package com.twinkle.JakSim.model.service.account;

import com.twinkle.JakSim.model.dao.account.UserDao;
import com.twinkle.JakSim.model.dto.account.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    @Transactional
    public int CreateMember(HashMap<Object, String> member){
        System.out.println(" " +  member);

        UserDto userDto = new UserDto();

        userDto.setId(member.get("id"));
        userDto.setPw(passwordEncoder.encode(member.get("pw")));
        userDto.setName(member.get("name"));
        userDto.setGender(Integer.parseInt(member.get("gender")));
        userDto.setTel(member.get("tel"));
        userDto.setQuestion(Integer.parseInt(member.get("question")));
        userDto.setAnswer(member.get("answer"));
        userDto.setBirth(member.get("birth"));
        userDto.setRole(Integer.parseInt(member.get("role")));

        //정상
        return userDao.insertMember(userDto);
    }

    public UserDto findByUsername(String id) {
        return userDao.findByUserId(id);
    }

    public UserDto findByTel(String tel) {
        return userDao.findByTel(tel);
    }

    public int update(String user_id, String pw) {
        return userDao.updatePassword(user_id, passwordEncoder.encode(pw));
    }

    public boolean checkPassword(String username, String pw) {
        return passwordEncoder.matches(pw, userDao.findByUserId(username).getPw());
    }

    public int delete(String id) {
        return userDao.deleteUser(id);
    }

}
