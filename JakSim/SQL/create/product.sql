DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
    TP_IDX INT(8) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    USER_ID VARCHAR(30) NOT NULL,
    TP_TITLE VARCHAR(100) NOT NULL,
    TP_PRICE INT(7) NOT NULL,
    TP_TYPE INT(1) NOT NULL,
    TP_PERIOD INT(2) NOT NULL,
    TP_TIMES INT(3) NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID) ON DELETE CASCADE
);

COMMIT;

INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '바디 프로필 1개월 패키지(개인)', 560000, 1, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '바디 프로필 1개월 패키지(단체)', 480000, 2, 30, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '바디 프로필 3개월 패키지(개인)', 840000, 1, 90, 24);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '바디 프로필 3개월 패키지(단체)', 756000, 2, 90, 36);

VALUES(NULL, 'angel1', '생활 근력 1개월 패키지(개인)', 500000, 1, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '생활 근력 1개월 패키지(단체)', 400000, 2, 30, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '생활 근력 3개월 패키지(개인)', 800000, 1, 90, 24);
INSERT INTO PRODUCT
VALUES(NULL, 'angel1', '생활 근력 3개월 패키지(단체)', 700000, 2, 90, 36);



INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '체력 강화 1개월 패키지(개인)', 400000, 1, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '체력 강화 1개월 패키지(단체)', 320000, 2, 30, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '체력 강화 3개월 패키지(개인)', 700000, 1, 90, 24);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '체력 강화 3개월 패키지(단체)', 616000, 2, 90, 36);

VALUES(NULL, 'angel2', '생활 근력 1개월 패키지(개인)', 400000, 1, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '생활 근력 1개월 패키지(단체)', 300000, 2, 30, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '생활 근력 3개월 패키지(개인)', 700000, 1, 90, 24);
INSERT INTO PRODUCT
VALUES(NULL, 'angel2', '생활근력 3개월 패키지(단체)', 600000, 2, 90, 36);



INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '자세 교정 1개월 패키지(개인)', 400000, 1, 30, 4);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '체력 강화 1개월 패키지(단체)', 400000, 2, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '체력 강화 3개월 패키지(개인)', 840000, 1, 90, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '체력 강화 3개월 패키지(단체)', 840000, 2, 90, 24);

VALUES(NULL, 'angel3', '생활 근력 1개월 패키지(개인)', 560000, 1, 30, 8);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '생활 근력 1개월 패키지(단체)', 600000, 2, 30, 12);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '생활 근력 3개월 패키지(개인)', 908000, 1, 90, 24);
INSERT INTO PRODUCT
VALUES(NULL, 'angel3', '생활 근력 3개월 패키지(단체)', 980000, 2, 90, 36);

COMMIT;