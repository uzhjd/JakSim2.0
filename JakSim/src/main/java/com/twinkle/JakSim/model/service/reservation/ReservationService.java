package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.account.UserDao;
import com.twinkle.JakSim.model.dao.reservation.ReservationDao;
import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PseudoColumnUsage;

@Service
public class ReservationService {

    @Autowired
    private DataSource ds;
    private ReservationDao reservationDao;
    private TimetableDao timetableDao;
    private UserDao userDao;

    public String register(ReservationDto reservationDto) {
        boolean resAvailable = true;

        try {
            // 해당 날에 대한 예약이 없을 경우
            boolean isReservate = reservationDao.isReservate(reservationDto.getUserId(), reservationDto.getRCDt());

            // pt권 수가 남아있는지
            boolean isPtTicket = userDao.isPtTicket(reservationDto.getUserId());

            // time table idx가 실제하는 데이터인지
            boolean isTimetable = timetableDao.isTimetable(reservationDto.getTIdx());

        } catch (Exception e) {
            resAvailable = false;
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("예약을 할 수 없는 환경입니다.");
        }

        // 등록 시도
        if(resAvailable) {
            reservationDao.register(reservationDto);
        }

        return "";
    }
}
