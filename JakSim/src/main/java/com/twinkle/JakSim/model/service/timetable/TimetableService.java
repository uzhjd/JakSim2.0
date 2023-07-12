package com.twinkle.JakSim.model.service.timetable;

import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableDao timetableDao;

    public List<TimetableDto> findMyTrainerTimetable(String trainerId) {
        List<TimetableDto> timetableDtoList = new ArrayList<>();

        try {
            timetableDtoList = timetableDao.findAllTimetable(trainerId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableDtoList;
    }

    public Optional<TimetableDto> findMyTimetableRecent(String username){
        return timetableDao.findMyTimetableRecent(username);
    }

    public Optional<TimetableDto> findMyTimetableSoon(String username) {
        List<TimetableDto> timeList = timetableDao.findMyTimetableSoon(username).orElseThrow();
        if(timeList.isEmpty()){
            return Optional.empty();
        }

        for(TimetableDto time : timeList){
            System.out.println(time.getTStartT());
            System.out.println(LocalTime.now());
            System.out.println(time.getTStartT().getHour() - LocalTime.now().getHour());
            System.out.println(time.getTStartT().getMinute() - LocalTime.now().getMinute());
            System.out.println(time.getTStartT().compareTo(LocalTime.now()));
        }

        return Optional.ofNullable(timeList.get(0));
    }
}
