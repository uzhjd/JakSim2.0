package com.twinkle.JakSim.model.service.trainer;

import com.twinkle.JakSim.model.dao.trainer.TrainerDao;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private TrainerDao trainerDao;

    public TrainerDto myTrainer(int utIdx) {
        TrainerDto trainerInfo = null;

        try {
            trainerInfo = trainerDao.findAllValidPt(utIdx);
        } catch (Exception e) {
            System.out.println(e);
        }

        return trainerInfo;
    }

}
