package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dao.reservation.ReservationDao;
import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDao;
    private final TimetableDao timetableDao;
    private final PaymentDao paymentDao;

    public int register(String userId, ReservationRequest reservationDto) {
        ReservationResponse reservationResponse = reservationDao.findReservation(userId, reservationDto.getTrainerId(),
                                                                                            reservationDto.getDate());

        boolean isPtTicket = paymentDao.isPtTicket(reservationDto.getP_idx());

        boolean isTimetable = timetableDao.isTimetable(reservationDto.getT_idx());
        System.out.println(reservationResponse.toString());
        if(reservationResponse.getRIdx() != 0) {
            return 1;
        } else if(!isPtTicket)
            return 2;
        else if(!isTimetable)
            return 3;

        try {
            boolean register = reservationDao.register(userId, reservationDto.getT_idx(), reservationDto.getP_idx());

            if(register)
                paymentDao.decreasePt(reservationDto.getPtCnt(), reservationDto.getP_idx());
            else
                return 4;
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        return 5;
    }

    public Boolean delete(int pIdx, int rIdx) {
        if(reservationDao.delete(rIdx)) {
            paymentDao.increaseCnt(pIdx);

            return true;
        }

        return false;
    }

    public ReservationResponse findReservation(String userId, String trainerId, LocalDate tDate) {
        ReservationResponse reservationResponse = new ReservationResponse();

        reservationResponse = reservationDao.findReservation(userId, trainerId, tDate);

        return reservationResponse;
    }
}
