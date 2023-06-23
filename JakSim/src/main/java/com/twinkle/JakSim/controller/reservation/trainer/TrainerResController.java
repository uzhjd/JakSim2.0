package com.twinkle.JakSim.controller.reservation.trainer;

import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reservation/manage")
@RequiredArgsConstructor
public class TrainerResController {

    private final ReservationService reservationService;

    @GetMapping("/cancle/{pIdx}/{rIdx}/")
    public String resCancle(@PathVariable("rIdx") int rIdx, @PathVariable("pIdx") int pIdx, Model model) {
        if(reservationService.delete(pIdx, rIdx)) {
            model.addAttribute("trainerList", "삭제 성공");
        } else {
            model.addAttribute("trainerList", "삭제 실패");
        }

        return "content/scheduleList/scheduleList";
    }
}
