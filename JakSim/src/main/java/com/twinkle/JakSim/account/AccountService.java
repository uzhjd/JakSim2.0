package com.twinkle.JakSim.account;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private DataSource ds;
    private UserDao userDao;

    @Transactional
    public int CreateMember(HashMap<Object, String> member){
        UserDO userDO = new UserDO();

        userDO.setId(member.get("id"));
        userDO.setPw(member.get("pw"));
        userDO.setName(member.get("name"));
        userDO.setGender(Integer.parseInt(member.get("birth")));
        userDO.setTel(member.get("tel"));
        userDO.setQuestion(Integer.parseInt(member.get("question")));
        userDO.setAnswer(member.get("answer"));
        userDO.setBirth(member.get("birth"));
        userDO.setC_dt(member.get("c_dt"));
        userDO.setRole(Integer.parseInt(member.get("role")));

        //정상
        return userDao.insertMember(userDO);
    }
}
