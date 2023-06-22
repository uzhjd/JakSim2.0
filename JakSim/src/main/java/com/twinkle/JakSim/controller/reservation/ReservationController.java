package com.twinkle.JakSim.controller.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationDto;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import javax.validation.Valid;

@Controller
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

        return "content/scheduleList/scheduleList";
    }

    @GetMapping("/cancle/{pIdx}/{rIdx}/")
    public String resCancle(@AuthenticationPrincipal User user, @PathVariable("rIdx") int rIdx,
                                @PathVariable("pIdx") int pIdx, Model model) {
        if(reservationService.delete(user.getUsername(), pIdx, rIdx)) {
            model.addAttribute("trainerList", "삭제 성공");
        } else {
            model.addAttribute("trainerList", "삭제 실패");
        }

        return "content/scheduleList/scheduleList";
    }
}
