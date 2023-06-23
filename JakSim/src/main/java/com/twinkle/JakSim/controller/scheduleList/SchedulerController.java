package com.twinkle.JakSim.controller.scheduleList;

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
public class SchedulerController {

    private final TrainerService trainerService;
    private final PaymentService paymentService;
    private final TimetableService timetableService;
    private final ScheduleListService scheduleListService;

    @GetMapping("/ptList")
    public String TrainerList(@AuthenticationPrincipal User user, Model model) {
        try {
            List<ValidPtDto> validPtList = paymentService.findValidPtList(user.getUsername());
            model.addAttribute("trainerList", validPtList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/generalScheduleList";
    }

    @GetMapping("/details/{trainerId}")
    public String ScheulerDetails(@AuthenticationPrincipal User user, @PathVariable("trainerId") String trainerId,
                                                                                                        Model model) {

        try {
            List<TimetableDto> scheduleList = scheduleListService.findSchedule(user.getUsername(), trainerId);
            model.addAttribute("trainerList", scheduleList);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/generalScheduleList";
    }

    @GetMapping("/trainerDetails/{trainerId}")
    public String TrainerDetails(@PathVariable("trainerId") String trainerId, Model model) {
        try {
            TrainerDetailDto trainerDetailDto = trainerService.findMyTrainer(trainerId);
            model.addAttribute("trainerDetails", trainerDetailDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "content/scheduleList/generalScheduleList";
    }

    @GetMapping("/timetableDetails/{trainerId}")
    public String TimetableDetails(@AuthenticationPrincipal User user, @PathVariable("trainerId") String trainerId,
                                                                                                        Model model) {
        try {
            List<TimetableDto> timetableDtoList = timetableService.findMyTrainerTimetable(trainerId);
            model.addAttribute("trainerDetails", timetableDtoList);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Role도 authentics에 넣어달라
        // 아니면 쿼리문 날려서 찾아야 한다. 또는 따로 매서드 만들어야 함.
//        if(user.getRoles().toString().equals(1)) {
//            return "content/scheduleList/generalScheduleList";
//        }

        return "content/scheduleList/trainerScheduleList";
    }
}
