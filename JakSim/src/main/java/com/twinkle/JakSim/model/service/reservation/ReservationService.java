package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.account.UserDao;
import com.twinkle.JakSim.model.dao.payment.PaymentDao;
import com.twinkle.JakSim.model.dao.reservation.ReservationDao;
import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private DataSource ds;
    private ReservationDao reservationDao;
    private TimetableDao timetableDao;
    private PaymentDao paymentDao;

    public Boolean register(ReservationDto reservationDto) {
        boolean resAvailable = true;
        boolean result = true;

        try {
            // 해당 날에 대한 예약이 없을 경우
            boolean isReservate = reservationDao.isReservate(reservationDto.getUserId(), reservationDto.getRCDt());

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
            result = reservationDao.register(reservationDto.getTIdx(), reservationDto.getUserId(), reservationDto.getRCDt());
        }

        return result;
    }
}
