package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dao.account.UserRowMapper;
import com.twinkle.JakSim.model.dao.timetable.TimetableRowMapper;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.dto.trainer.response.TrainerDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrainerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;

    public void insertTrainer(TrainerInsertDto trainer, String userId){
        this.sql = "INSERT INTO TRAINER_DETAILS VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, trainer.getIntroduce(), trainer.getInsta(),
                trainer.getGym(), userId, trainer.getExpert1(), trainer.getExpert2());

        this.sql = "INSERT INTO PRODUCT VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        for(int i=0; i<trainer.getPtTimes().length; i++) {
            jdbcTemplate.update(sql, userId, trainer.getPtTimes()[i],
                    trainer.getPtPrice()[i], trainer.getPtType()[i], trainer.getPtTitle()[i],
                    trainer.getPtPeriod()[i]);
        }

        this.sql = "INSERT INTO TRAINER_CAREER VALUES(NULL, ?, ?)";

        for(String content : trainer.getCareerContent()) {
            jdbcTemplate.update(sql, userId, content);
        }

        this.sql = "INSERT INTO TRAINER_CERT VALUES(NULL, ?, ?, ?)";

        for(String cert : trainer.getCertName()) {
            jdbcTemplate.update(sql, userId, cert, trainer.getCertImage());
        }

        this.sql = "INSERT INTO TRAINER_IMAGE VALUES(NULL, ?, ?)";

        for(String image : trainer.getImagePath()) {
            jdbcTemplate.update(sql, userId, image);
        }

        this.sql = "UPDATE USER_INFO SET USER_ROLE = 2 WHERE USER_ID = ?";  //유저정보 변경
        jdbcTemplate.update(sql, userId);

    }
    public List<TrainerSearchDto> getAllTrainerForSearch() {
        String sql = "SELECT DISTINCT td.user_id, td.UT_IDX, ti.TI_PATH, td.UT_GYM, ui.user_name, " +
                "td.UT_EXPERT_1, td.UT_EXPERT_2, tc.TC_NAME, ROUND(AVG(r.R_STAR), 1) AS AVG_R_STAR" +
                " FROM trainer_details td" +
                " JOIN product p ON td.user_id = p.user_id" +
                " JOIN trainer_career tca ON td.user_id = tca.user_id" +
                " JOIN trainer_cert tc ON td.user_id = tc.user_id" +
                " JOIN trainer_image ti ON td.user_id = ti.user_id" +
                " JOIN user_info ui ON td.user_id = ui.user_id" +
                " LEFT JOIN review r ON td.UT_IDX = r.UT_IDX" +
                " GROUP BY td.user_id" +
                " ORDER BY AVG_R_STAR DESC, td.UT_IDX ASC";

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper());
    }

    public List<TrainerSearchDto> getAllTrainerForMainPage() {
        String sql = "SELECT DISTINCT td.user_id, td.UT_IDX, ti.TI_PATH, td.UT_GYM, ui.user_name, " +
                "td.UT_EXPERT_1, td.UT_EXPERT_2, tc.TC_NAME, ROUND(AVG(r.R_STAR), 1) AS AVG_R_STAR" +
                " FROM trainer_details td" +
                " JOIN product p ON td.user_id = p.user_id" +
                " JOIN trainer_career tca ON td.user_id = tca.user_id" +
                " JOIN trainer_cert tc ON td.user_id = tc.user_id" +
                " JOIN trainer_image ti ON td.user_id = ti.user_id" +
                " JOIN user_info ui ON td.user_id = ui.user_id" +
                " LEFT JOIN review r ON td.UT_IDX = r.UT_IDX" +
                " GROUP BY td.user_id" +
                " ORDER BY td.UT_IDX DESC" +
                " LIMIT 3";;

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper());
    }


    public UserDto getTrainerName(String userId) {
        String sql = "SELECT DISTINCT * " +
                "FROM trainer_details td " +
                "JOIN user_info ui ON td.user_id = ui.user_id " +
                "WHERE td.user_id = ?";

        List<UserDto> resultList = jdbcTemplate.query(sql, new UserRowMapper(), userId);

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    public List<TrainerPageDto> getTrainerPage(String userId) {   //리스트아닌것들
        String sql = "SELECT DISTINCT tc.TC_IMAGE, td.UT_EXPERT_1, td.UT_EXPERT_2, ui.user_name, td.UT_GYM, td.UT_INSTA, td.UT_INTRO " +
                "FROM trainer_details td " +
                "JOIN user_info ui ON td.user_id = ui.user_id " +
                "JOIN trainer_cert tc ON td.user_id = tc.user_id " +
                "WHERE td.user_id = ?";

        return jdbcTemplate.query(sql, new TrainerPageRowMapper(), userId);
    }

    public List<ProductDto> getProduct(String userId) {    //리스트들
        this.sql = "select * from product where user_id = ?";

        return jdbcTemplate.query(sql, new ProductRowMapper(), userId);
    }
    public List<TrainerCareerDto> getCareer(String userId) {    //리스트들
        this.sql = "select * from trainer_career where user_id = ?";

        return jdbcTemplate.query(sql, new TrainerCareerRowMapper(), userId);
    }
    public List<TrainerCertDto> getCert(String userId) {    //리스트들
        this.sql = "select * from trainer_cert where user_id = ?";

        return jdbcTemplate.query(sql, new TrainerCertRowMapper(), userId);
    }

    public List<TrainerImageDto> getTrainerImage(String userId) {    //리스트들
        this.sql = "select * from trainer_image where user_id = ?";

        return jdbcTemplate.query(sql, new TrainerImageRowMapper(), userId);
    }

    public List<TrainerSearchDto> getTrainerForTrainerPage(String userId) {
        String sql = "select * from trainer_details td " +
                "join product p on td.user_id = p.user_id " +
                "join trainer_career tca on td.user_id = tca.user_id " +
                "join trainer_cert tc on td.user_id = tc.user_id " +
                "join trainer_image ti on td.user_id = ti.user_id " +
                "join user_info ui on td.user_id = ui.user_id " +
                "where td.user_id = ?";

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper(), userId);
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


        for(int i=0; i<trainer.getPtTimes().length; i++) {
            this.sql = "UPDATE PRODUCT " +
                "SET TP_TIMES = ?," +
                "TP_PRICE = ?," +
                "TP_TYPE = ?," +
                "TP_TITLE = ?," +
                "TP_PERIOD = ? " +
                "WHERE TP_IDX = ?";

            System.out.println("dao : "+trainer.getPtId()[i]);

            jdbcTemplate.update(sql, trainer.getPtTimes()[i],
                    trainer.getPtPrice()[i], trainer.getPtType()[i], trainer.getPtTitle()[i],
                    trainer.getPtPeriod()[i], trainer.getPtId()[i]);
        }

        for(int i=0; i<trainer.getCareerContent().length; i++) {
            this.sql = "UPDATE TRAINER_CAREER " +
                "SET TCAR_CONTENT = ? " +
                "WHERE TCAR_IDX = ?";

            jdbcTemplate.update(sql, trainer.getCareerContent()[i], trainer.getCareerId()[i]);
        }
        if(trainer.getCertImage() != null) {
            for(int i=0; i<trainer.getCertName().length; i++) {
                this.sql = "UPDATE TRAINER_CERT " +
                        "SET TC_NAME = ?," +
                        "TC_IMAGE = ? " +
                        "WHERE TC_IDX = ?";

                jdbcTemplate.update(sql, trainer.getCertName()[i], trainer.getCertImage(), trainer.getCertId()[i]);
            }
        }
        else if(trainer.getCertImage() == null) {
            System.out.println("CertImage NUll!");
            for(int i=0; i<trainer.getCertName().length; i++) {
                this.sql = "UPDATE TRAINER_CERT " +
                        "SET TC_NAME = ? " +
                        "WHERE TC_IDX = ?";

                jdbcTemplate.update(sql, trainer.getCertName()[i], trainer.getCertId()[i]);
            }
        }

        if (trainer.getImagePath() != null) {
            this.sql = "DELETE FROM TRAINER_IMAGE WHERE USER_ID = ?";
            jdbcTemplate.update(sql, userId);

            this.sql = "INSERT INTO TRAINER_IMAGE VALUES(NULL, ?, ?)";
            for(String image : trainer.getImagePath()) {
                jdbcTemplate.update(sql, userId, image);
            }
        }
        else if(trainer.getImagePath() == null) {
            System.out.println("ImagePath null!!!!");
        }

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

        this.sql = "DELETE FROM TIMETABLE WHERE USER_ID = ?";
        jdbcTemplate.update(sql, userId);

    }

    public TrainerDetailResponse findTrainerBreif(String trainerId) {
        TrainerDetailResponse trainerDetailResponse = new TrainerDetailResponse();

        this.sql = "select * from trainer_details as t inner join user_info as u on t.user_id = u.user_id " +
                "inner join trainer_image as img on u.user_id = img.user_id " +
                "where t.user_id = ?";

        try {
            trainerDetailResponse = jdbcTemplate.queryForObject(this.sql, new TrainerDetailRowMapper(), trainerId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }

        return trainerDetailResponse;
    }

    public List<TimetableResponse> getTimetable(String userId) {
        this.sql = "SELECT * FROM TIMETABLE WHERE USER_ID = ?";

        return jdbcTemplate.query(this.sql, new TimetableRowMapper(), userId);
    }

    public void registerTimetable(TimetableResponse timetable) {
        this.sql = "INSERT INTO TIMETABLE VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(this.sql, timetable.getUserId(), timetable.getTDate(),         //DATE는 안넣어도 될 것 같은데..timetable.getTDate(),
                timetable.getTStartT(), timetable.getTEndT(),
                timetable.getTPeople(), timetable.getTType());
    }

    public void updateTimetable(TimetableResponse timetable, String userId) {
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

    public List<PtUserDto> getPtUserInfo(String userId) {
        this.sql = "SELECT UI.USER_ID, UI.USER_NAME, UI.USER_TEL, UI.USER_GENDER, PD.TP_TYPE, P.P_PT_CNT " +
                   "FROM USER_INFO UI JOIN PAYMENT P ON UI.USER_ID = P.USER_ID " +
                   "JOIN PRODUCT PD ON P.TP_IDX = PD.TP_IDX WHERE PD.USER_ID = ?";

        return jdbcTemplate.query(this.sql, new PtUserRowMapper(), userId);
    }

    public TrainerDetailResponse searchByUsername(String userId) {
        this.sql = "SELECT T.UT_IDX, T.USER_ID, U.USER_NAME, U.USER_GENDER, T.UT_INSTA, T.UT_GYM, T.UT_EXPERT_1, T.UT_EXPERT_2, I.TI_PATH " +
                "FROM TRAINER_DETAILS T, USER_INFO U, TRAINER_IMAGE I " +
                "WHERE T.USER_ID = ? " +
                "AND T.USER_ID = U.USER_ID AND T.USER_ID = I.USER_ID ";
        TrainerDetailResponse trainer = new TrainerDetailResponse();
        try{
            trainer = jdbcTemplate.queryForObject(sql, new TrainerDetailRowMapper(), userId);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }
        return trainer;
    }
}
