package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dao.account.UserRowMapper;
import com.twinkle.JakSim.model.dao.reservation.ReservationRowMapper;
import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PaymentDao {
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public PaymentDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Boolean isPtTicket(int pIdx) {
        boolean result = false;
        PaymentDto paymentDto = new PaymentDto();

        this.sql = "select * from payment where p_idx = ?";

        try {
            paymentDto = jdbcTemplate.queryForObject(this.sql, new PaymentRowMappper(), pIdx);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            System.out.println("Thers isn't any information");
        } catch (Exception e) {
            System.out.println(e);
        }

        int pCnt = paymentDto.getPPtCnt();

        if(pCnt > 0) {
            result = true;
        }

        return result;
    }

    public void decreasePt(int pIdx) {
        this.sql = "update payment " +
                "set p_pt_cnt = ? where p_idx = ? limit 1";

        try {
            jdbcTemplate.update(this.sql, pIdx);
        } catch (Exception e) {
            System.out.println(e);
        }

        return;
    }
}
