package com.twinkle.JakSim.model.service.timetable;

import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
