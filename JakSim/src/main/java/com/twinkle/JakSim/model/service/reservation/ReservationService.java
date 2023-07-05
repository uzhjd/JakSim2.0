package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dao.reservation.ReservationDao;
import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationCkRequest;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDao;
    private final TimetableDao timetableDao;
    private final PaymentDao paymentDao;

    public int register(String userId, ReservationRequest reservationDto) throws Exception {
        boolean isReservated = reservationDao.isReservate(userId, reservationDto.getTDate());

        boolean isPtTicket = paymentDao.isPtTicket(reservationDto.getPIdx());

        boolean isTimetable = timetableDao.isTimetable(reservationDto.getTIdx());

        if(!isReservated)
            return 1;
        else if(!isPtTicket)
            return 2;
        else if(!isTimetable)
            return 3;

        try {
            boolean register = reservationDao.register(userId, reservationDto.getTIdx(), reservationDto.getPIdx());

            if(register)
                paymentDao.decreasePt(reservationDto.getPIdx());
            else
                return 4;
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        // 0: 예약 완료, 1: 등록된 예약 O, 2: 사용가능한 PT권 X, 일정 존재 X, 4: 예기치 못한 에러 발생
        return 0;
    }

    public Boolean delete(int pIdx, int rIdx) {
        if(reservationDao.delete(rIdx)) {
            paymentDao.increaseCnt(pIdx);

            return true;
        }

        return false;
    }

    public ReservationResponse findReservation(String userId, String trainerId, LocalDate tDate) {
        ReservationResponse reservationResponse = reservationDao.findReservation(userId, trainerId, tDate);

        return reservationResponse;
    }
}
