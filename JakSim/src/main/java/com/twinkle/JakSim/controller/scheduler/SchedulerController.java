package com.twinkle.JakSim.controller.scheduler;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerController {

    @Autowired
    private DataSource ds;

    private ReservationService reservationService;

    @PostMapping("")
//    @GetMapping("") // Test용
    public String scheduleList(@Valid @RequestBody ReservationDto reservationDto) {

        LocalDate today = LocalDate.now();

       // 조회한 날이 오늘 이후인지 체크해보기 (과거에 것은 예약이 안되게 막기)
        if(reservationDto.getRCDt().compareTo(today) >= 0) {
            System.out.println("입력된 날이 더 과거입니다.");
            reservationService.register(reservationDto);
        }

        return "content/scheduler";
    }

}
