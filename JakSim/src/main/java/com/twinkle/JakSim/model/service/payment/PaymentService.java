package com.twinkle.JakSim.model.service.payment;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentService {

    @Autowired
    private DataSource ds;

    private PaymentDao paymentDao;

    public List<ValidPtDto> validPtList(String userId) {
        List<ValidPtDto> list = new ArrayList<>();
        LocalDate today = LocalDate.now();

        try {
            list = paymentDao.findAllValidPt(userId, today);
        } catch (NullPointerException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("예약을 할 수 없는 환경입니다.");
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
