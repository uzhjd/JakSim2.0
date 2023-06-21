package com.twinkle.JakSim.controller.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/register")
    public String resRegister(@Valid @RequestBody ReservationDto reservationDto) {
        LocalDate today = LocalDate.now();

        if(reservationDto.getRCDt().compareTo(today) >= 0) {
            System.out.println("입력된 날이 더 과거입니다.");
            reservationService.register(reservationDto);
        }

        return "content/reservation/register";
    }
}
