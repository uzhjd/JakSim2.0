package com.twinkle.JakSim.account;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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

        UserDO userDO = new UserDO();

        userDO.setId(member.get("id"));
        userDO.setPw(passwordEncoder.encode(member.get("pw")));
        userDO.setName(member.get("name"));
        userDO.setGender(Integer.parseInt(member.get("gender")));
        userDO.setTel(member.get("tel"));
        userDO.setQuestion(Integer.parseInt(member.get("question")));
        userDO.setAnswer(member.get("answer"));
        userDO.setBirth(member.get("birth"));
        userDO.setRole(Integer.parseInt(member.get("role")));

        UserDao userDao = new UserDao(ds);
        //정상
        return userDao.insertMember(userDO);
    }
}
