package com.twinkle.JakSim.model.service.payment;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dto.payment.PaymentDo;
import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDao paymentDao;

    public List<ValidPtDto> findValidPtList(String userId) {
        List<ValidPtDto> list = new ArrayList<>();
        LocalDate today = LocalDate.now();

        try {
            list = paymentDao.findAllValidPt(userId, today);
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public PaymentDo getRecentPayment(String username) {
        return paymentDao.findRecentByUsername(username);
    }
}
