package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dto.trainer.PtUserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PtUserRowMapper implements RowMapper<PtUserDto> {
    @Override
    public PtUserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PtUserDto ptUserDto = new PtUserDto();

        ptUserDto.setUserName(rs.getString(""));
        ptUserDto.setUserId(rs.getString(""));
        ptUserDto.setGender(rs.getInt(""));
        ptUserDto.setTel(rs.getString(""));
        ptUserDto.setPtType(rs.getInt(""));
        ptUserDto.setPtCnt(rs.getInt(""));

        return ptUserDto;
    }
}
