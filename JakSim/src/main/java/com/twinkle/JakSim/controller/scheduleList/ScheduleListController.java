package com.twinkle.JakSim.controller.scheduleList;

import com.twinkle.JakSim.model.dto.reservation.request.ReservationCkRequest;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
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
    private final ReservationService reservationService;

    // 3
    @GetMapping("/details/trainerId/{trainerId}")
    public ResponseEntity<List<TimetableResponse>> monthSchedule(@AuthenticationPrincipal User user,
                                                                      @PathVariable("trainerId") String trainerId) {

        List<TimetableResponse> response = scheduleListService.findSchedule(user.getUsername(), trainerId);

        return ResponseEntity.ok(response);
    }

    // 5
    @PostMapping("/reservation")
    public ResponseEntity<ReservationResponse> reservation(@AuthenticationPrincipal User user,
                                                           @RequestBody ReservationCkRequest resCheckRequest) {

        ReservationResponse response = reservationService.findReservation(user.getUsername(), resCheckRequest.getTrainerId(), resCheckRequest.getTDate());

        return ResponseEntity.ok(response);
    }
}
