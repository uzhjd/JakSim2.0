package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dao.reservation.ReservationDao;
import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationDao reservationDao;
    private final TimetableDao timetableDao;
    private final PaymentDao paymentDao;

    public Boolean register(ReservationDto reservationDto) {
        boolean resAvailable = true;
        boolean result = true;

        try {
            // 해당 날에 대한 예약이 없을 경우
//            boolean isReservate = reservationDao.isReservate(reservationDto.getUserId(), reservationDto.getTDate());

            // pt권 수가 남아있는지
            boolean isPtTicket = paymentDao.isPtTicket(reservationDto.getTIdx());

            // time table idx가 실제하는 데이터인지
            boolean isTimetable = timetableDao.isTimetable(reservationDto.getTIdx());

        } catch (NullPointerException e) {
            resAvailable = false;
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("예약을 할 수 없는 환경입니다.");
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        // 등록 시도
        if(resAvailable) {
            result = reservationDao.register(reservationDto.getTIdx(), reservationDto.getUserId());
        }

        if(result) {
            paymentDao.decreasePt(reservationDto.getPIdx());
        }

        return result;
    }

    public Boolean delete(String userId, int pIdx, int rIdx) {
        if(reservationDao.delete(rIdx)) {
            paymentDao.increaseCnt(userId, pIdx);
            return true;
        }

        return false;
    }
}
