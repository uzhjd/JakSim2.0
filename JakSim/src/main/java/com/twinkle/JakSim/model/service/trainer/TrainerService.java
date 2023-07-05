package com.twinkle.JakSim.model.service.trainer;

import com.twinkle.JakSim.model.dao.trainer.*;
import com.twinkle.JakSim.model.dto.timetable.TimetableInsertDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    //트레이너 등록
    @Transactional
    public void TrainerSignUp(TrainerInsertDto requestTrainer, String userId) {
        trainerDao.insertTrainer(requestTrainer, userId);

    }

    @Transactional
    public List<TrainerSearchDto> searchAllTrainer() {
        return trainerDao.getAllTrainerForSearch();
    }

    //트레이너 상세페이지에 표시될 정보 가져오기
    @Transactional
    public List<TrainerSearchDto> searchTrainer(int tIdx) {
        return trainerDao.getTrainerPage(tIdx);
    }

    @Transactional
    public void updateTrainer(TrainerInsertDto requestTrainer, String userId) {
        trainerDao.upDateTrainer(requestTrainer, userId);

    }

    @Transactional
    public void deleteTrainer(String userId) {
        trainerDao.deleteTrainer(userId);

    }

    @Transactional
    public List<TimetableResponse> getTimetable(String userId) {
        return trainerDao.getTimetable(userId);
    }

    @Transactional
    public void registerTimetable(TimetableInsertDto timetable, String userId) {
        TimetableResponse timetableDto = new TimetableResponse(timetable.getTIdx(), userId, LocalDate.parse(timetable.getTDate()),
                LocalTime.parse(timetable.getTStartT()), LocalTime.parse(timetable.getTEndT()),
                timetable.getTPeople(), timetable.getTType());

        trainerDao.registerTimetable(timetableDto);

    }

    @Transactional
    public void updateTimetable(TimetableResponse timetable, String userId) {
        trainerDao.updateTimetable(timetable, userId);
    }

    @Transactional
    public void deleteTimetable(int tIdx) {
        trainerDao.deleteTimetable(tIdx);
    }

    @Transactional
    public List<PtUserDto> getMyPtUserInfo(String userId) {
        return trainerDao.getPtUserInfo(userId);
    }


    public TrainerDetailResponse findTrainerBreif(String trainerId) {
        TrainerDetailResponse trainerDetailResponse = new TrainerDetailResponse();

        try {
            trainerDetailResponse = trainerDao.findTrainerBreif(trainerId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return trainerDetailResponse;
    }

}
