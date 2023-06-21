package com.twinkle.JakSim.model.service.trainer;

import com.twinkle.JakSim.model.dao.trainer.*;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    //트레이너 등록
    @Transactional
    public void TrainerSignUp(TrainerInsertDto requestTrainer) {
        trainerDao.insertTrainer(requestTrainer);

    }

    @Transactional
    public List<TrainerSearchDto> searchAllTrainer() {
        return trainerDao.getAllTrainerForSearch();
    }

    //트레이너 상세페이지에 표시될 정보 가져오기
    @Transactional
    public List<TrainerSearchDto> searchTrainer(int idx) {
        return trainerDao.getTrainerPage(idx);
    }

    @Transactional
    public void updateTrainer(TrainerInsertDto requestTrainer) {
        trainerDao.upDateTrainer(requestTrainer);

    }

    @Transactional
    public void deleteTrainer(TrainerInsertDto requestTrainer) {
        trainerDao.deleteTrainer(requestTrainer);

    }

    public TrainerDetailDto myTrainer(String trainerId) {
        TrainerDetailDto trainerDetailDto = new TrainerDetailDto();

        try {
            trainerDetailDto = trainerDao.findMyTrainer(trainerId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return trainerDetailDto;
    }

}
