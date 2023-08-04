DROP TABLE TRAINER_DETAILS;
DROP TABLE TRAINER_IMAGE;
DROP TABLE TRAINER_CERT;
DROP TABLE TRAINER_CAREER;
DROP TABLE PRODUCT;


CREATE TABLE TRAINER_DETAILS (
    UT_IDX INT(8) NOT NULL AUTO_INCREMENT,
    UT_INTRO VARCHAR(600) NOT NULL,
    UT_INSTA VARCHAR(100) NULL,
    UT_GYM VARCHAR(300) NOT NULL,
    USER_ID VARCHAR(30) NOT NULL,
    UT_EXPERT_1 INT(1) NOT NULL CHECK (UT_EXPERT_1 BETWEEN 0 AND 5),
    UT_EXPERT_2 INT(1) NOT NULL CHECK (UT_EXPERT_2 BETWEEN 0 AND 5),
    UT_ADDRESS VARCHAR(300) NOT NULL,
    PRIMARY KEY (UT_IDX),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO (USER_ID)
    ON DELETE CASCADE
);


CREATE TABLE TRAINER_IMAGE(
    TI_IDX INT(8) AUTO_INCREMENT,
    USER_ID VARCHAR(30) NOT NULL,
    TI_PATH VARCHAR(1000) NOT NULL,
    PRIMARY KEY (TI_IDX),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID)
    ON DELETE CASCADE
);

CREATE TABLE TRAINER_CERT(
    TC_IDX INT(8) AUTO_INCREMENT,
    USER_ID VARCHAR(30) NOT NULL,
    TC_NAME VARCHAR(100) NOT NULL,
    TC_IMAGE VARCHAR(1000) NOT NULL,
    PRIMARY KEY (TC_IDX),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID)
    ON DELETE CASCADE
);


CREATE TABLE TRAINER_CAREER(
    TCAR_IDX INT(8) AUTO_INCREMENT,
    USER_ID VARCHAR(30) NOT NULL,
    TCAR_CONTENT VARCHAR(100) NOT NULL,
    PRIMARY KEY (TCAR_IDX),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID)
    ON DELETE CASCADE
);

CREATE TABLE PRODUCT(
    TP_IDX INT(8) AUTO_INCREMENT,
    USER_ID VARCHAR(30) NOT NULL,
    TP_TIMES INT(3) NOT NULL,
    TP_PRICE INT(7) NOT NULL,
    TP_TYPE INT(1) NOT NULL,
    TP_TITLE VARCHAR(100) NOT NULL,
    TP_PERIOD INT(2) NOT NULL,
    PRIMARY KEY (TP_IDX),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID)
    ON DELETE CASCADE
);

COMMIT;

--//트레이너는 직접 등록해야함
--INSERT INTO TRAINER_DETAILS
--VALUES(NULL, '새로운 작심득근의 새로운 트레이너!', 'https://www.instagram.com/gse96', '나주배짐', 'wkdgyfla97', 1, 2, '경기도');
--INSERT INTO TRAINER_DETAILS
--VALUES(NULL, '작심득근 리부트 가보자고', 'https://www.instagram.com/humbleKim', '강짐', 'hye8997', 2, 3, '서울');
--INSERT INTO TRAINER_DETAILS
--VALUES(NULL, '멸치탈출 트레이너는 나야나', 'https://www.instagram.com/test', '가디짐', 'humble', 2, 4, '경기도');
--INSERT INTO TRAINER_DETAILS
--VALUES(NULL, '오늘밤 주인공은 나야나', 'https://www.instagram.com/tes9t6', '근육짱짐', 'ujeong', 3, 5, '경기도');
--INSERT INTO TRAINER_DETAILS
--VALUES(NULL, '안녕 여기 1짱은 나다', 'https://www.instagram.com/test96', '가리봉짱짐', 'test2', 2, 5, '경기도');
--
--INSERT INTO TRAINER_IMAGE
--VALUES(NULL, 'wkdgyfla97', 'files/trainer/a.jpg');
--INSERT INTO TRAINER_IMAGE
--VALUES(NULL, 'hye8997', 'files/trainer/b.jpg');
--INSERT INTO TRAINER_IMAGE
--VALUES(NULL, 'humble', 'files/trainer/c.jpg');
--INSERT INTO TRAINER_IMAGE
--VALUES(NULL, 'ujeong', 'files/trainer/d.jpg');
--INSERT INTO TRAINER_IMAGE
--VALUES(NULL, 'test96', 'files/trainer/e.jpg');
--
--
--INSERT INTO TRAINER_CERT
--VALUES(NULL, 'wkdgyfla97', '생활체육지도자 1급', 'files/trainer/cert/1.png');
--INSERT INTO TRAINER_CERT
--VALUES(NULL, 'hye8997', '줘패기 1급', 'files/trainer/cert/2.png');
--INSERT INTO TRAINER_CERT
--VALUES(NULL, 'humble', '말빨 1급', 'files/trainer/cert/3.png');
--INSERT INTO TRAINER_CERT
--VALUES(NULL, 'ujeong', '귀여움 1급', 'files/trainer/cert/4.png');
--INSERT INTO TRAINER_CERT
--VALUES(NULL, 'test96', '가리봉 싸움 1짱', 'files/trainer/cert/5.png');
--
--INSERT INTO TRAINER_CAREER
--VALUES(NULL, 'wkdgyfla97', '전국체전 입선');
--INSERT INTO TRAINER_CAREER
--VALUES(NULL, 'hye8997', '줘패기대회 입선');
--INSERT INTO TRAINER_CAREER
--VALUES(NULL, 'humble', '입털기 대회 우승');
--INSERT INTO TRAINER_CAREER
--VALUES(NULL, 'ujeong', '귀여움 대회 아차상');
--INSERT INTO TRAINER_CAREER
--VALUES(NULL, 'test96', '가리봉 싸움짱');


INSERT INTO PRODUCT
VALUES(NULL, 'wkdgyfla97', 24, 600000, 0, '바디프로필 3개월 패키지', 6);
INSERT INTO PRODUCT
VALUES(NULL, 'hye8997', 10, 200000, 0, '생활근력 만들기', 5);
INSERT INTO PRODUCT
VALUES(NULL, 'humble', 20, 400000, 1, '입근육을 만들자', 6);
INSERT INTO PRODUCT
VALUES(NULL, 'ujeong', 1, 10000, 0, '귀여워지고싶나용', 6);
INSERT INTO PRODUCT
VALUES(NULL, 'test2', 1, 20000, 0, '뒤지게패자', 6);


COMMIT;


SELECT DISTINCT td.user_id, td.UT_IDX, ti.TI_PATH, td.UT_GYM, ui.user_name, td.UT_EXPERT_1, td.UT_EXPERT_2, td.UT_ADDRESS, tc.TC_NAME, ROUND(AVG(r.R_STAR), 1) AS AVG_R_STAR
FROM trainer_details td
JOIN product p ON td.user_id = p.user_id
JOIN trainer_career tca ON td.user_id = tca.user_id
JOIN trainer_cert tc ON td.user_id = tc.user_id
JOIN trainer_image ti ON td.user_id = ti.user_id
JOIN user_info ui ON td.user_id = ui.user_id
LEFT JOIN review r ON td.UT_IDX = r.UT_IDX
where ut_expert_1 = 2 or ut_expert_2 = 5
GROUP BY td.user_id
ORDER BY AVG_R_STAR DESC, td.UT_IDX DESC
LIMIT 0, 10;