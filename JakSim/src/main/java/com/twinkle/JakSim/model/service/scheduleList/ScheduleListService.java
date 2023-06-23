package com.twinkle.JakSim.model.service.scheduleList;

import com.twinkle.JakSim.model.dao.scheduleList.ScheduleListDao;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleListService {

    private final ScheduleListDao scheduleListDao;

    public List<TimetableDto> findSchedule(String userId, String trainerId) {
        List<TimetableDto> timetableList = new ArrayList<>();

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

    public List<TimetableDto> findAllSchedule(String trainerId, int tType) {
        List<TimetableDto> timetableList = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate firstDate = today.withDayOfMonth(1);
        LocalDate lastDate = today.withDayOfMonth(firstDate.lengthOfMonth());

        try {
            timetableList = scheduleListDao.findAllSchedule(trainerId, firstDate, lastDate, tType);
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableList;
    }
}
