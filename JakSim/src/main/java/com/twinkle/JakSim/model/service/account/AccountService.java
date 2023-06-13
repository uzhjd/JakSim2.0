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
    @Autowired
    private DataSource ds;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public int CreateMember(HashMap<Object, String> member){
        System.out.println("service: " +  member);

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

        UserDao userDao = new UserDao(ds);
        //정상
        return userDao.insertMember(userDto);
    }
}
