package com.twinkle.JakSim.model.dao.payment;

import com.twinkle.JakSim.model.dao.trainer.ProductRowMapper;
import com.twinkle.JakSim.model.dto.payment.PaymentDo;
import com.twinkle.JakSim.model.dto.payment.response.PaymentDto;
import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.dto.trainer.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PaymentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public Boolean isPtTicket(int pIdx) {
        boolean result = false;

        this.sql = "select p_pt_cnt from payment where p_idx = ?";

        try {
            PaymentDto paymentDto = jdbcTemplate.queryForObject(this.sql, new PaymentRowMappper(), pIdx);

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
        this.sql = "update payment set P_PT_CNT = P_PT_CNT + 1 where p_idx = ?";

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

    public Optional<PaymentDo> findRecentByUsername(String username) {
        String sql = "SELECT * FROM PAYMENT WHERE USER_ID = ?";
        PaymentDo paymentDo = null;
        try{
            paymentDo = jdbcTemplate.queryForObject(sql, new PaymentDoRowMapper(), username);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return Optional.ofNullable(paymentDo);
    }

    public int getTotalPage(String username) {
        String sql = "SELECT COUNT(*) FROM PAYMENT WHERE USER_ID = ?";
        int result =  0;

        try{
            result = Objects.requireNonNull(jdbcTemplate.queryForObject(sql, Integer.class, username)).intValue();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<PaymentDo> getRecentByPage(String username, int page, int pageSize){
        String sql = "SELECT * FROM PAYMENT WHERE USER_ID = ? ORDER BY P_IDX DESC LIMIT ? OFFSET ?";
        int offset = (page - 1) * pageSize;
        List<PaymentDo> payList = new ArrayList<>();

        try{
            payList = jdbcTemplate.query(sql, new PaymentDoRowMapper(), username, pageSize, offset);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return payList;
    }

    public Optional<PaymentDo> getPaymentByIdx(int pIdx) {
        String sql = "SELECT * FROM PAYMENT WHERE P_IDX = ?";
        PaymentDo paymentDo = null;

        try{
            paymentDo = jdbcTemplate.queryForObject(sql, new PaymentDoRowMapper(), pIdx);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return Optional.ofNullable(paymentDo);
    }

    public ProductDto getProductByIdx(int tpIdx) {
        String sql = "SELECT * FROM PRODUCT WHERE TP_IDX = ?";
        ProductDto productDto = null;

        try{
            productDto = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), tpIdx);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return productDto;
    }
}
