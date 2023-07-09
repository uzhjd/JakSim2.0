package com.twinkle.JakSim.model.dto.reservation.response;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private int rIdx;

    private String tStartT;

    private String tEndT;

    private int tPeople;

    private int tType;
//
//    public void setTStartT(String time) {
//        this.tStartT = LocalTime.parse(time);
//    }
//
//    public void setTEndT(String time) {
//        this.tEndT = LocalTime.parse(time);
//    }
}
