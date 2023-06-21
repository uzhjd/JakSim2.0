package com.twinkle.JakSim.controller.scheduleList;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.dto.scheduleList.response.ScheduleListInfo;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.timetable.TimetableService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class ScheduleListController {

    private final TrainerService trainerService;
    private final PaymentService paymentService;
    private final TimetableService timetableService;

    @GetMapping("/ptList")
    public String TrainerList(@AuthenticationPrincipal User user, Model model) {
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            List<ValidPtDto> validPtList = paymentService.validPtList(user.getUsername());
            model.addAttribute("trainerList", validPtList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

    @PostMapping("/trainerDetails")
    public String TrainerDetails(String trainerId, Model model) {
        // request: DTo로 받아오는게 아니니까 null체크라던지 trainerId가 실제로 내 DB에 있는지 체크 필요?
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            TrainerDetailDto trainerDetailDto = trainerService.myTrainer(trainerId);
            model.addAttribute("trainerDetails", trainerDetailDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

    @PostMapping("/timetableDetails")
    public String TimetableDetails(String trainerId, Model model) {
        // request: DTo로 받아오는게 아니니까 null체크라던지 trainerId가 실제로 내 DB에 있는지 체크 필요?
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            List<TimetableDto> timetableDtoList = timetableService.myTrainerTimetable(trainerId);
            model.addAttribute("trainerDetails", timetableDtoList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

//        // 예약 현황 real 스케줄러 조회 2번
//        // 상세 예약 현황 조회 (얘는 따료 빼지만 호출만 하기 service 꺼)
//        // reservationService.resDetail(???);
}
