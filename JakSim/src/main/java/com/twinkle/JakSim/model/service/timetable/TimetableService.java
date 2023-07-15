//package com.twinkle.JakSim.model.service.timetable;
//
//import com.twinkle.JakSim.model.dao.timetable.TimetableDao;
//import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class TimetableService {
//
//    private final TimetableDao timetableDao;
//
//    public List<TimetableResponse> findMyTrainerTimetable(String userId, String trainerId) {
//        List<TimetableResponse> timetableDtoList = new ArrayList<>();
//
//        try {
//            timetableDtoList = timetableDao.findAllTimetable(trainerId);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return timetableDtoList;
//    }
//}
