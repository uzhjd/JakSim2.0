package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.dto.payment.response.PtTicketResponse;
import com.twinkle.JakSim.model.dto.product.response.ValidPtResponse;
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

        this.sql = "select p_pt_cnt from payment where p_idx = ?";

        try {
            PtTicketResponse paymentDto = jdbcTemplate.queryForObject(this.sql, new PaymentRowMappper(), pIdx);

            if(paymentDto.getPPtCnt() > 0) {
                result = true;
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            System.out.println("Thers isn't any information");
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    // INSERT INTO PAYMENT (USER_ID, TP_IDX, P_PT_CNT, P_PT_PERIOD, P_REFUND)
    //VALUES ('ujeong', 2, 3, 3, 0);
//    public Boolean savePaymentDetails(String userId, ApproveResponse paymentDetails) {
//        Boolean result = true;
//
//        this.sql = "insert into payment (user_id, tp_idx, p_c_dt, p_refund, p_pt_cnt, p_pt_period) " +
//                "values (?, ?, ?, 0, ?, ?)";
//
//        try {
//                                                                                                    // 횟수, pt기간
//            jdbcTemplate.update(this.sql, userId, paymentDetails.getItem_code(), paymentDetails.getCreated_at(), );
//        } catch (EmptyResultDataAccessException e) {
//            result = false;
//            System.out.println(e);
//        }
//
//        return result;
//    }
    public void decreasePt(int pIdx) {
        this.sql = "update payment set p_pt_cnt = ? where p_idx = ? limit 1";

        try {
            jdbcTemplate.update(this.sql, pIdx-1);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }

        return;
    }

    public void increaseCnt(int pIdx) {
        this.sql = "update payment set P_PT_CNT = P_PT_CNT + 1 where p_idx = ?";

        try {
            jdbcTemplate.update(this.sql, pIdx);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return;
    }

    public List<ValidPtResponse> findAllValidPt(String userId, LocalDate today) {
        List<ValidPtResponse> list = new ArrayList<>();

        this.sql = "select pro.user_id, pro.tp_type, u.user_name, pay.p_idx, pay.p_pt_cnt " +
                "from payment as pay inner join product as pro on pay.tp_idx = pro.tp_idx " +
                "inner join user_info as u on pro.user_id = u.user_id " +
                "where pay.user_id = ? and p_refund = '0' and p_pt_cnt > '0' and p_pt_period >= (? - p_c_dt)";

        list = jdbcTemplate.query(this.sql, new ValidPtRowMapper(), userId, today);

        return list;
    }
}
