package com.twinkle.JakSim.controller.timetable;

import com.twinkle.JakSim.model.dto.timetable.request.TimetableRequest;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimetableRestController {

    ScheduleListService scheduleListService;

    // 4
    @PostMapping("/details")
    public ResponseEntity<List<TimetableResponse>> myTrainerTimetable(@RequestBody TimetableRequest timetableRequest) {

        List<TimetableResponse> response = scheduleListService.findTrainerTimetable(timetableRequest.getTrainerId(),
                timetableRequest.getTDate(), timetableRequest.getTType());

        return ResponseEntity.ok(response);
    }
}
