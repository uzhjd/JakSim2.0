package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
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
        this.sql = "update payment set p_pt_cnt = ? where p_idx = ? limit 1";

        try {
            jdbcTemplate.update(this.sql, pIdx);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }

        return;
    }

    public void increaseCnt(int pIdx) {
        this.sql = "update payment set P_PT_CNT = P_PT_CNT + 1 where and p_idx = ?";

        try {
            jdbcTemplate.update(this.sql, pIdx);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return;
    }

    public List<ValidPtDto> findAllValidPt(String userId, LocalDate today) {
        List<ValidPtDto> list = new ArrayList<>();

        this.sql = "select pro.user_id and pro.tp_idx and pay.p_pt_cnt " +
                "from payment as pay inner join product as pro on pay.tp_idx and pro.tp_idx " +
                "where user_id = ? and p_refund = '0' and p_pt_cnt > '0' and p_pt_period >= (? - p_c_dt)";

        try {
            list = jdbcTemplate.query(this.sql, new ValidPtRowMapper(), userId, today);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There's no valid Pt list");
            System.out.println("e.getMessage() = " + e.getMessage());

            list = null;
        }

        return list;
    }
}
