package com.twinkle.JakSim.controller.scheduler;

import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerController {

    @Autowired
    private DataSource ds;

    private ReservationService reservationService;
/// gse test
//    @GetMapping("/{userId}")
    @GetMapping("") // Test용
    public String scheduleList(@RequestBody ReservationDto reservationDto) {

        reservationService.register(reservationDto);

        return "content/scheduler";
    }

}
