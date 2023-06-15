package com.twinkle.JakSim.controller.scheduler;

import com.twinkle.JakSim.model.dto.reservation.ReservationDto;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

//    @GetMapping("/{userId}")
    @GetMapping("") // Test용
    public String scheduleList(@Valid @RequestBody ReservationDto reservationDto) {

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formatedNow = now.format(formatter);

        // 조회한 날이 오늘 이후인지 체크해보기 (과거에 것은 예약이 안되게 막기)
        if(reservationDto.getRCDt().compareTo(formatedNow) >= 0) {
            System.out.println("입력된 날이 더 과거입니다.");
            reservationService.register(reservationDto);
        }

        return "content/scheduler";
    }

}
