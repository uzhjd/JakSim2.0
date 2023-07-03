package com.twinkle.JakSim.model.dto.account;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginLogDto {
    private int id;
    private String user_id;
    private String ip;
    private String dt;
}
