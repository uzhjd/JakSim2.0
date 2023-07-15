package com.twinkle.JakSim.model.service.trainer;

import com.twinkle.JakSim.model.dao.trainer.*;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.timetable.TimetableInsertDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    //트레이너 등록
    @Transactional
    public void TrainerSignUp(TrainerInsertDto requestTrainer, String userId, MultipartFile certImage, MultipartFile[] imagePath) throws IOException {
        if(!certImage.isEmpty() || certImage != null) {
            String projectPath = System.getProperty("user.dir") +
                    "/JakSim/src/main/resources/static/image/trainer";

            UUID uuid = UUID.randomUUID();
            String certName = uuid + "_" + certImage.getOriginalFilename();
            File saveFile = new File(projectPath, certName);
            requestTrainer.setCertImage("/image/trainer/" + certName);
            certImage.transferTo(saveFile);
        }
        else {
            System.out.println("자격증 이미지없음");
        }

        if(imagePath.length != 0  || imagePath != null) {
            String projectPath = System.getProperty("user.dir") +
                    "/JakSim/src/main/resources/static/image/trainer";
            UUID uuid = UUID.randomUUID();
            List<String> imagePaths = new ArrayList<>();

            for(int i = 0; i < imagePath.length; i++) {
                String imageName = uuid + "_" + imagePath[i].getOriginalFilename();
                File saveFile2 = new File(projectPath, imageName);
                imagePath[i].transferTo(saveFile2);
                imagePaths.add("/image/trainer/" + imageName);
            }
            requestTrainer.setImagePath(imagePaths.toArray(new String[0]));
        }
        else {
            System.out.println("이미지패스없음");
        }

        trainerDao.insertTrainer(requestTrainer, userId);

    }
    @Transactional
    public void updateTrainer(TrainerInsertDto requestTrainer, String userId, MultipartFile certImage, MultipartFile[] imagePath) throws IOException {
        if(!certImage.isEmpty()) {
            String projectPath = System.getProperty("user.dir") +
                    "/JakSim/src/main/resources/static";

            if(requestTrainer.getCertImage() != null) {
                String path = projectPath + requestTrainer.getCertImage();

                File file = new File(path);

                if (file.exists()) {
                    file.delete();
                }


                UUID uuid = UUID.randomUUID();
                String certName = uuid + "_" + certImage.getOriginalFilename();
                File saveFile = new File(projectPath, certName);
                requestTrainer.setCertImage("/image/trainer/" + certName);
                certImage.transferTo(saveFile);
            }
        }
        else {
            System.out.println("자격증 이미지없음");
        }

        if(imagePath.length == 0) {
            String projectPath = System.getProperty("user.dir") +
                    "/JakSim/src/main/resources/static";

            if(requestTrainer.getImagePath().length==0 || requestTrainer.getImagePath() != null) {
                for (String image : requestTrainer.getImagePath()) {
                    String path = projectPath + image;

                    File file = new File(path);

                    if(file.exists()) {
                        file.delete();
                    }
                }
            }
            String projectPath2 = System.getProperty("user.dir") +
                    "/JakSim/src/main/resources/static/image/trainer";
            UUID uuid = UUID.randomUUID();
            List<String> imagePaths = new ArrayList<>();

            for(int i = 0; i < imagePath.length; i++) {
                String imageName = uuid + "_" + imagePath[i].getOriginalFilename();
                File saveFile2 = new File(projectPath2, imageName);
                imagePath[i].transferTo(saveFile2);
                imagePaths.add("/image/trainer/" + imageName);
            }
            requestTrainer.setImagePath(imagePaths.toArray(new String[0]));
        }
        else {
            System.out.println("이미지패스없음");
        }

        trainerDao.upDateTrainer(requestTrainer, userId);

    }
    @Transactional
    public List<TrainerSearchDto> searchAllTrainer() {
        return trainerDao.getAllTrainerForSearch();
    }

    @Transactional
    public List<TrainerSearchDto> searchTrainerForMainPage() {
        return trainerDao.getAllTrainerForMainPage();
    }

    @Transactional
    public UserDto searchTrainerName(String userId) {
        return trainerDao.getTrainerName(userId);
    }

    //트레이너 상세페이지에 표시될 정보 가져오기
    @Transactional
    public List<TrainerPageDto> searchTrainer(String userId) {
        return trainerDao.getTrainerPage(userId);
    }

    @Transactional
    public List<ProductDto> getProduct(String userId) {
        return trainerDao.getProduct(userId);
    }

    @Transactional
    public List<TrainerCareerDto> getCareer(String userId) {
        return trainerDao.getCareer(userId);
    }

    @Transactional
    public List<TrainerCertDto> getCert(String userId) {
        return trainerDao.getCert(userId);
    }

    @Transactional
    public List<TrainerImageDto> getTrainerImage(String userId) {
        return trainerDao.getTrainerImage(userId);
    }

    @Transactional
    public List<TrainerSearchDto> searchTrainerForTrainerPage(String userId) {
        return trainerDao.getTrainerForTrainerPage(userId);
    }



    @Transactional
    public void deleteTrainer(TrainerInsertDto trainerDto, String userId) {
        //System.out.println("서비스 del : " + trainerDto.getImagePath().toString());

        String projectPath = System.getProperty("user.dir") +
                "/JakSim/src/main/resources/static";

        if(trainerDto.getCertImage() != null) {
            String path = projectPath + trainerDto.getCertImage();

            File file = new File(path);

            if(file.exists()) {
                file.delete();
            }
        }

        if(trainerDto.getImagePath().length==0 || trainerDto.getImagePath() != null) {
            for (String image : trainerDto.getImagePath()) {
                String path = projectPath + image;

                File file = new File(path);

                if(file.exists()) {
                    file.delete();
                }
            }

        }

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
