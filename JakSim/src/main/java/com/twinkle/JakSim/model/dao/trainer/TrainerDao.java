package com.twinkle.JakSim.model.dao.trainer;

import com.twinkle.JakSim.model.dao.account.UserRowMapper;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;
    private String userId = "hye8997";
    public void insertTrainer(TrainerInsertDto trainer){
        this.sql = "INSERT INTO TRAINER_DETAILS VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, trainer.getIntroduce(), trainer.getInsta(),
                     trainer.getGym(), userId, trainer.getExpert1(), trainer.getExpert2());

        this.sql = "INSERT INTO PRODUCT VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,userId, trainer.getPtTimes(),
                trainer.getPtPrice(), trainer.getPtType(), trainer.getPtTitle(),
                trainer.getPtPeriod());

        this.sql = "INSERT INTO TRAINER_CAREER VALUES(NULL, ?, ?)";

        jdbcTemplate.update(sql, userId, trainer.getCareerContent());

        this.sql = "INSERT INTO TRAINER_CERT VALUES(NULL, ?, ?, ?)";

        jdbcTemplate.update(sql, userId, trainer.getCertName(), trainer.getCertImage());

        this.sql = "INSERT INTO TRAINER_IMAGE VALUES(NULL, ?, ?)";

        jdbcTemplate.update(sql, userId, trainer.getImagePath());

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

    public List<TrainerSearchDto> getTrainerPage(int tIdx) {
        String sql = "select * from trainer_details td " +
                     "join product p on td.user_id = p.user_id " +
                     "join trainer_career tca on td.user_id = tca.user_id " +
                     "join trainer_cert tc on td.user_id = tc.user_id " +
                     "join trainer_image ti on td.user_id = ti.user_id " +
                     "join user_info ui on td.user_id = ui.user_id";

        return jdbcTemplate.query(sql, new TrainerSearchRowMapper(), tIdx);
    }

    public void upDateTrainer(TrainerInsertDto trainer){
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

    public void deleteTrainer(TrainerInsertDto trainer){
        this.sql = "DELETE FROM TRAINER_DETAILS WHERE USER_ID = ?";

        jdbcTemplate.update(sql,  userId);

        this.sql = "DELETE FROM PRODUCT WHERE USER_ID = ?";

        jdbcTemplate.update(sql,  userId);

        this.sql = "DELETE FROM TRAINER_CAREER WHERE USER_ID = ?";

        jdbcTemplate.update(sql,  userId);

        this.sql = "DELETE FROM TRAINER_CERT WHERE USER_ID = ?";

        jdbcTemplate.update(sql,  userId);

        this.sql = "DELETE FROM TRAINER_IMAGE WHERE USER_ID = ?";

        jdbcTemplate.update(sql,  userId);

    }

}
