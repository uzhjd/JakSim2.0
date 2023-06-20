package com.twinkle.JakSim.controller.scheduleList;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class ScheduleListController {

    private final TrainerService trainerService;
    private final PaymentService paymentService;
    private TrainerDto trainerInfo;

    @PostMapping
    public String SchedulePage(@AuthenticationPrincipal User user, Model model) {
        try {
            // Username = userID??
            List<ValidPtDto> validPtList = paymentService.validPtList(user.getUsername());
            int ptCnt = validPtList.get(0).getPPtCnt();
            //trainerInfo = trainerService.myTrainer(validPtList.get(0).getUtIdx());

        } catch (NullPointerException e) {
            System.out.println("There's no any valid Pt list");
            return "";
        } catch (Exception e) {
            System.out.println(e);
        }

        model.addAttribute("schedulerInfo", schedulerInfo);


        // 예약 현황


        // pt선생님 timetable조회 (얘는 따료 빼지만 호출만 하기 service 꺼)
        // timetableService.myTrainerTimetble(???);

        // 상세 예약 현황 조회 (얘는 따료 빼지만 호출만 하기 service 꺼)
        // reservationService.resDetail(???);

        return "content/scheduler/scheduler";
    }
}
