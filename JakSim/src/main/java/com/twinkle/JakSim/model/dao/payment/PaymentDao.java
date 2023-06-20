package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public Boolean isPtTicket(int pIdx) {
        boolean result = false;
        PaymentDto paymentDto = new PaymentDto();

        this.sql = "select * from payment where p_idx = ?";

        try {
            PaymentDto paymentDto = jdbcTemplate.queryForObject(this.sql, new PaymentRowMappper(), pIdx);
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

    public List<ValidPtDto> findAllValidPt(String userId, LocalDate today) {
        this.sql = "select pro.ut_idx and pay.p_pt_cnt from payment pay inner join product pro on pay.tp_idx and pro.tp_idx" +
                " where userId = ? and p_refund = '0' and p_pt_cnt > '0' and pro.pt_period >= (?today - p_c_dt)";

        try {
            List<ValidPtDto> list = jdbcTemplate.query(this.sql, new ValidPtRowMapper(), userId, today);
        } catch (NullPointerException e) {
            System.out.println("There's no valid Pt list");
            System.out.println("e.getMessage() = " + e.getMessage());

            list = null;
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
