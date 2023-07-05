package com.twinkle.JakSim.controller.scheduleList;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import com.twinkle.JakSim.model.service.timetable.TimetableService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final TimetableService timetableService;
    private final ScheduleListService scheduleListService;

//    @GetMapping()
//    public String generalScheduler(@AuthenticationPrincipal User user, Model model) {
//
//
////        if(user.getAuthorities().equals("ROLE_USER")) {
////            return "content/scheduleList/generalScheduleList";
////        }
//
//        return "content/scheduleList/generalScheduleList";
//    }

    @GetMapping("/details/trainerId/{trainerId}")
    public ResponseEntity<List<TimetableDto>> generalscheDetails(@AuthenticationPrincipal User user,
                                                                 @PathVariable("trainerId") String trainerId) {
        List<TimetableDto> response = scheduleListService.findSchedule(user.getUsername(), trainerId);

        return ResponseEntity.ok(response);
    }

    ////////////////////////////////////////////////
//    @GetMapping("/trainerDetails/{trainerId}")
//    public ResponseEntity<TrainerDetailDto> TrainerDetails(@PathVariable("trainerId") String trainerId) {
//        TrainerDetailDto response = trainerService.findMyTrainer(trainerId);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/timetableDetails/{trainerId}")
//    public ResponseEntity<List<TimetableDto>> TimetableDetails(@AuthenticationPrincipal User user,
//                                                               @PathVariable("trainerId") String trainerId) {
//        List<TimetableDto> response = timetableService.findMyTrainerTimetable(trainerId);
//
//        return ResponseEntity.ok(response);
//    }
}
