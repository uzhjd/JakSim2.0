package com.twinkle.JakSim.model.service.reservation;

import com.twinkle.JakSim.model.dao.ReservationDao;
import com.twinkle.JakSim.model.dto.ReservationDto;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private DataSource ds;
    private ReservationDao reservationDao;

    public String register(ReservationDto reservationDto) {
        boolean resAvailable = reservationDao.resAvailable();

        if(resAvailable) {
            reservationDao.register(reservationDto);
        }

        return "";
    }
}
