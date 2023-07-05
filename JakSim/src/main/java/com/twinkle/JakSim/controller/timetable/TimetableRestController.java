package com.twinkle.JakSim.controller.timetable;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimetableRestController {

    private final ScheduleListService scheduleListService;

    @GetMapping("/details/{tType}")
    public ResponseEntity<List<TimetableDto>> trainerscheDetails(@AuthenticationPrincipal User user,
                                                                 @PathVariable("tType") int tType) {

        List<TimetableDto> response = scheduleListService.findAllSchedule(user.getUsername(), tType);

        return ResponseEntity.ok(response);
    }
}
