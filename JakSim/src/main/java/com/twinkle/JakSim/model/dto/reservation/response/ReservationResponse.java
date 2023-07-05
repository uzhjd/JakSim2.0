package com.twinkle.JakSim.model.dto.reservation.response;

import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

    private int rIdx;

    private LocalTime tStartT;

    private LocalTime tEndT;

    private int tPeople;

    private int tType;
}
