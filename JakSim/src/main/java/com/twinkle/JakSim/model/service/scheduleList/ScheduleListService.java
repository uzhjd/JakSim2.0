package com.twinkle.JakSim.model.service.scheduleList;

import com.twinkle.JakSim.model.dao.scheduleList.ScheduleListDao;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleListService {

    private final ScheduleListDao scheduleListDao;

    public List<TimetableResponse> findSchedule(String userId, String trainerId) {
        List<TimetableResponse> timetableList = new ArrayList<>();

        // 트레이너가 실제하는지에 대한 익셉션 처리 필요
        LocalDate today = LocalDate.now();
        LocalDate firstDate = today.withDayOfMonth(1);
        LocalDate lastDate = today.withDayOfMonth(firstDate.lengthOfMonth());

        try {
            timetableList = scheduleListDao.findSchedule(userId, firstDate, lastDate, trainerId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableList;
    }

    public List<TimetableResponse> findTrainerTimetable(String trainerId, String date, int tType) {
        List<TimetableResponse> timetableList = new ArrayList<>();

        try {
            timetableList = scheduleListDao.findTrainerSchedule(trainerId, date, tType);

            // 트레이너가 실제하는지에 대한 익셉션 처리 필요
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableList;
    }
}
