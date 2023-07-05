package com.twinkle.JakSim.controller.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationRequest;
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
    public String resRegister(@AuthenticationPrincipal User user, @Valid @RequestBody ReservationRequest reservationDto,
                                                                                        Model model) throws Exception {
        LocalDate today = LocalDate.now();

        if(reservationDto.getTDate().compareTo(today) >= 0) {
            System.out.println("입력된 날이 더 과거입니다.");
            int result = reservationService.register(user.getUsername(), reservationDto);

            if(result == 1) {
                throw new Exception("이미 등록된 예약이 있습니다.");
            } else if (result == 2) {
                throw new Exception("PT권이 모두 사용되었습니다.");
            } else if (result == 3) {
                throw new Exception("해당 일정은 존재하지 않습니다.");
            } else if (result == 4) {
                throw new Exception("예약이 올바르게 되지 않았습니다.");
            }

            model.addAttribute("예약 결과", result);

        }

        return "content/scheduleList/generalScheduleList";
    }

    @GetMapping("/cancle/{pIdx}/{rIdx}/")
    public String resCancle(@AuthenticationPrincipal User user, @PathVariable("rIdx") int rIdx,
                            @PathVariable("pIdx") int pIdx, Model model) {

        if(reservationService.delete(pIdx, rIdx)) {
            model.addAttribute("trainerList", "삭제 성공");
        } else {
            model.addAttribute("trainerList", "삭제 실패");
        }

        // Role도 authentics에 넣어달라
        // 아니면 쿼리문 날려서 찾아야 한다. 또는 따로 매서드 만들어야 함.
//        if(user.getRoles().toString().equals(1)) {
//            return "content/scheduleList/generalScheduleList";
//        }

        return "content/scheduleList/trainerScheduleList";
    }
}
