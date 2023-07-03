package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dao.account.UserRowMapper;
import com.twinkle.JakSim.model.dao.timetable.TimetableRowMapper;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;
    //private String userId = "hye8997";
    public void insertTrainer(TrainerInsertDto trainer, String userId){
        this.sql = "INSERT INTO TRAINER_DETAILS VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, trainer.getIntroduce(), trainer.getInsta(),
                trainer.getGym(), userId, trainer.getExpert1(), trainer.getExpert2());

        this.sql = "INSERT INTO PRODUCT VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, trainer.getPtTimes(),
                trainer.getPtPrice(), trainer.getPtType(), trainer.getPtTitle(),
                trainer.getPtPeriod());

        this.sql = "INSERT INTO TRAINER_CAREER VALUES(NULL, ?, ?)";
        jdbcTemplate.update(sql, userId, trainer.getCareerContent());

        this.sql = "INSERT INTO TRAINER_CERT VALUES(NULL, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, trainer.getCertName(), trainer.getCertImage());

        this.sql = "INSERT INTO TRAINER_IMAGE VALUES(NULL, ?, ?)";
        jdbcTemplate.update(sql, userId, trainer.getImagePath());

        this.sql = "UPDATE USER_INFO SET USER_ROLE = 2 WHERE USER_ID = ?";  //유저정보 변경
        jdbcTemplate.update(sql, userId);

    }

    public List<TrainerSearchDto> getAllTrainerForSearch() {
        String sql = "select * from trainer_details td " +
                "join product p on td.user_id = p.user_id " +
                "join trainer_career tca on td.user_id = tca.user_id " +
                "join trainer_cert tc on td.user_id = tc.user_id " +
                "join trainer_image ti on td.user_id = ti.user_id " +
                "join user_info ui on td.user_id = ui.user_id";

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper());
    }

    public List<TrainerSearchDto> getTrainerPage(int utIdx) {
        String sql = "select * from trainer_details td " +
                "join product p on td.user_id = p.user_id " +
                "join trainer_career tca on td.user_id = tca.user_id " +
                "join trainer_cert tc on td.user_id = tc.user_id " +
                "join trainer_image ti on td.user_id = ti.user_id " +
                "join user_info ui on td.user_id = ui.user_id " +
                "where ut_idx = ?";

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper(), utIdx);
    }

    public void upDateTrainer(TrainerInsertDto trainer, String userId){
        this.sql = "UPDATE TRAINER_DETAILS " +
                "SET UT_INTRO = ?," +
                "UT_INSTA = ?," +
                "UT_GYM = ?," +
                "UT_EXPERT_1 = ?," +
                "UT_EXPERT_2 = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(sql, trainer.getIntroduce(), trainer.getInsta(),
                trainer.getGym(), trainer.getExpert1(), trainer.getExpert2(), userId);

        this.sql = "UPDATE PRODUCT " +
                "SET TP_TIMES = ?," +
                "TP_PRICE = ?," +
                "TP_TYPE = ?," +
                "TP_TITLE = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(sql, trainer.getPtTimes(), trainer.getPtPrice(),
                trainer.getPtType(), trainer.getPtTitle(), userId);

        this.sql = "UPDATE TRAINER_CAREER " +
                "SET TCAR_CONTENT = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(sql, trainer.getCareerContent(), userId);

        this.sql = "UPDATE TRAINER_CERT " +
                "SET TC_NAME = ?," +
                "TC_IMAGE = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(sql, trainer.getCertName(), trainer.getCertImage(), userId);

        this.sql = "UPDATE TRAINER_IMAGE " +
                "SET TI_PATH = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(sql, trainer.getImagePath(), userId);

    }

    public void deleteTrainer(String userId){
        this.sql = "DELETE FROM TRAINER_DETAILS WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

        this.sql = "DELETE FROM PRODUCT WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

        this.sql = "DELETE FROM TRAINER_CAREER WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

        this.sql = "DELETE FROM TRAINER_CERT WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

        this.sql = "DELETE FROM TRAINER_IMAGE WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

        this.sql = "UPDATE USER_INFO SET USER_ROLE = 1 WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

    }

    public TrainerDetailDto findMyTrainer(String userId) {
        TrainerDetailDto trainerDetailDto = new TrainerDetailDto();

        this.sql = "select * from trainer_details where user_id = ?";

        try {
            trainerDetailDto = jdbcTemplate.queryForObject(this.sql, new TrainerDetailRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }

        return trainerDetailDto;
    }

    public List<TimetableDto> getTimetable(String userId) {
        this.sql = "SELECT * FROM TIMETABLE WHERE USER_ID = ?";

        return jdbcTemplate.query(this.sql, new TimetableRowMapper(), userId);
    }

    public void registerTimetable(TimetableDto timetable, String userId) {
        this.sql = "INSERT INTO TIMETABLE VALUES(NULL, ?, ?, ?, ?, ?, ?) " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, userId, timetable.getTDate(),         //DATE는 안넣어도 될 것 같은데..
                timetable.getTStartT(), timetable.getTEndT(), timetable.getTPeople(), timetable.getTType());
    }

    public void updateTimetable(TimetableDto timetable, String userId) {
        this.sql = "UPDATE TIMETABLE " +
                "SET T_START_T = ?, " +
                "T_END_T = ?, " +
                "T_PEOPLE = ?, " +
                "T_TYPE = ? " +
                "WHERE USER_ID = ?";

        jdbcTemplate.update(this.sql, timetable.getTStartT(), timetable.getTEndT(),
                timetable.getTPeople(), timetable.getTType(), userId);

    }

    public void deleteTimetable(int tIdx) {
        this.sql = "DELETE FROM TIMETABLE WHERE T_IDX = ?";
        jdbcTemplate.update(this.sql, tIdx);
    }

    public List<PtUserDto> getPtUserInfo(String userId) {                //값 여러개 넣어서 테스트 해봐야함
        this.sql = "SELECT UI.USER_ID, UI.USER_NAME, UI.USER_TEL, UI.USER_GENDER, PD.TP_TYPE, P.P_PT_PERIOD " +
                   "FROM USER_INFO UI JOIN PAYMENT P ON UI.USER_ID = P.USER_ID " +
                   "JOIN PRODUCT PD ON P.TP_IDX = PD.TP_IDX WHERE PD.USER_ID = ?";

        return jdbcTemplate.query(this.sql, new PtUserRowMapper(), userId);
    }

}
