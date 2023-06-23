package com.twinkle.JakSim.controller.scheduleList.generalUser;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import com.twinkle.JakSim.model.service.timetable.TimetableService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class GeneralScheController {

    private final TrainerService trainerService;
    private final PaymentService paymentService;
    private final TimetableService timetableService;
    private final ScheduleListService scheduleListService;

    @GetMapping("/ptList")
    public String TrainerList(@AuthenticationPrincipal User user, Model model) {
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            List<ValidPtDto> validPtList = paymentService.findValidPtList(user.getUsername());
            model.addAttribute("trainerList", validPtList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

    @GetMapping("/details/{trainerId}")
    public String ScheulerDetails(@AuthenticationPrincipal User user, @PathVariable("trainerId") String trainerId,
                                                                                                        Model model) {
        // request: DTo로 받아오는게 아니니까 null체크라던지 trainerId가 실제로 내 DB에 있는지 체크 필요?
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)LocalDate nMonth = LocalDate.now();

        try {
            List<TimetableDto> scheduleList = scheduleListService.findSchedule(user.getUsername(), trainerId);
            model.addAttribute("trainerList", scheduleList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

    @GetMapping("/trainerDetails/{trainerId}")
    public String TrainerDetails(@PathVariable("trainerId") String trainerId, Model model) {
        // request: DTo로 받아오는게 아니니까 null체크라던지 trainerId가 실제로 내 DB에 있는지 체크 필요?
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            TrainerDetailDto trainerDetailDto = trainerService.findMyTrainer(trainerId);
            model.addAttribute("trainerDetails", trainerDetailDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }

    @GetMapping("/timetableDetails/{trainerId}")
    public String TimetableDetails(@PathVariable("trainerId") String trainerId, Model model) {
        // request: DTo로 받아오는게 아니니까 null체크라던지 trainerId가 실제로 내 DB에 있는지 체크 필요?
        // response: (null 그대로 보내주고 back단에서 알림만 캐치해서 보내주는 방식으로하기)
        try {
            List<TimetableDto> timetableDtoList = timetableService.findMyTrainerTimetable(trainerId);
            model.addAttribute("trainerDetails", timetableDtoList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/scheduleList";
    }
}
