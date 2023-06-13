package com.twinkle.JakSim.model.dto.account;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String pw;
    private String name;
    private int gender;
    private String tel;
    private int question;
    private String answer;
    private String birth;
    private String c_dt;
    private int role;
}
