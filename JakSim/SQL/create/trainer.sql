CREATE TABLE TRAINER_DETAILS (
                                 UT_IDX INT(8) NOT NULL AUTO_INCREMENT,
                                 UT_INTRO VARCHAR(600) NOT NULL,
                                 UT_INSTA VARCHAR(100) NULL,
                                 UT_GYM VARCHAR(300) NOT NULL,
                                 USER_ID VARCHAR(30) NOT NULL,
                                 UT_EXPERT_1 INT(1) NOT NULL,
                                 UT_EXPERT_2 INT(1) NOT NULL,
                                 PRIMARY KEY (UT_IDX),
                                 FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID)
                                     ON DELETE CASCADE
);

CREATE TABLE TRAINER_IMAGE(
                              TI_IDX INT(8) AUTO_INCREMENT,
                              UT_IDX INT(8) NOT NULL,
                              TI_PATH VARCHAR(1000) NOT NULL,
                              PRIMARY KEY (TI_IDX),
                              FOREIGN KEY (UT_IDX) REFERENCES TRAINER_DETAILS(UT_IDX)
                                  ON DELETE CASCADE
);

CREATE TABLE TRAINER_CERT(
                             TC_IDX INT(8) AUTO_INCREMENT,
                             UT_IDX INT(8) NOT NULL,
                             TC_NAME VARCHAR(100) NOT NULL,
                             TC_IMAGE VARCHAR(1000) NOT NULL,
                             PRIMARY KEY (TC_IDX),
                             FOREIGN KEY (UT_IDX) REFERENCES TRAINER_DETAILS(UT_IDX)
                                 ON DELETE CASCADE
);

CREATE TABLE TRAINER_EXPERT(
                               TE_IDX INT(8) AUTO_INCREMENT,
                               UT_IDX INT(8) NOT NULL,
                               TE_EXPERT INT(1) NOT NULL,
                               PRIMARY KEY (TE_IDX),
                               FOREIGN KEY (UT_IDX) REFERENCES TRAINER_DETAILS(UT_IDX)
                                   ON DELETE CASCADE
);

CREATE TABLE TRAINER_CAREER(
                               TCAR_IDX INT(8) AUTO_INCREMENT,
                               UT_IDX INT(8) NOT NULL,
                               TCAR_CONTENT VARCHAR(100) NOT NULL,
                               PRIMARY KEY (TCAR_IDX),
                               FOREIGN KEY (UT_IDX) REFERENCES TRAINER_DETAILS(UT_IDX)
                                   ON DELETE CASCADE
);

CREATE TABLE PRODUCT(
                        TP_IDX INT(8) AUTO_INCREMENT,
                        UT_IDX INT(8) NOT NULL,
                        TP_TIMES INT(3) NOT NULL,
                        TP_PRICE INT(7) NOT NULL,
                        TP_TYPE INT(1) NOT NULL,
                        TP_TITLE VARCHAR(100) NOT NULL,
                        TP_PERIOD INT(2) NOT NULL,
                        PRIMARY KEY (TP_IDX),
                        FOREIGN KEY (UT_IDX) REFERENCES TRAINER_DETAILS (UT_IDX)
                            ON DELETE CASCADE
);
# TP_CONTENT VARCHAR(600) NOT NULL,



COMMIT;

INSERT INTO TRAINER_DETAILS
VALUES(NULL, '새로운 작심득근의 새로운 트레이너!', 'https://www.instagram.com/gse96', '나주배짐', 'wkdgyfla97', 1, 2);

INSERT INTO TRAINER_IMAGE
VALUES(NULL, 2, 'files/trainer/a.jpg');

INSERT INTO TRAINER_CERT
VALUES(NULL, 2, '생활체육지도자 1급', 'files/trainer/cert/massage.png');

INSERT INTO TRAINER_EXPERT
VALUES(NULL, 2, 0);

INSERT INTO TRAINER_CAREER
VALUES(NULL, 2, '전국체전 입선');

INSERT INTO PRODUCT
VALUES(NULL, 2, 24, 600000, 0, '바디프로필 3개월 패키지', 6);