package com.twinkle.JakSim.model.service.payment;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dto.product.response.ValidPtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDao paymentDao;

    public List<ValidPtResponse> findValidPtList(String userId) {
        List<ValidPtResponse> list = new ArrayList<>();
        LocalDate today = LocalDate.now();

        try {
            list = paymentDao.findAllValidPt(userId, today);

            if(list.isEmpty()) {
                System.out.println("유효한 pt권이 없습니다.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
