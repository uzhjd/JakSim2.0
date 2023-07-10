package com.twinkle.JakSim.model.dao.inbody;

import com.twinkle.JakSim.model.dto.inbody.InbodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InbodyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<InbodyDto> getInbodies(String username) {
        String sql = "SELECT * FROM INBODY WHERE USER_ID = ?";
        List<InbodyDto> inbodyList = new ArrayList<>();
        try{
            inbodyList = jdbcTemplate.query(sql, new InbodyRowMapper() ,username);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return inbodyList;
    }
}
