package com.twinkle.JakSim.controller.scheduleList;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class ScheduleListController {

    private final ScheduleListService scheduleListService;

    // 3
    @GetMapping("/details/{trainerId}/{today}")
    public ResponseEntity<List<TimetableResponse>> monthSchedule(@AuthenticationPrincipal User user,
                                                                      @PathVariable("trainerId") String trainerId,
                                                                 @PathVariable("today") String today) {

        List<TimetableResponse> response = scheduleListService.findSchedule(user.getUsername(), trainerId, today);

        return ResponseEntity.ok(response);
    }
}
