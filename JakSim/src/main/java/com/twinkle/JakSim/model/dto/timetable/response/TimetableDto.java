package com.twinkle.JakSim.model.dto.timetable.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableDto {

    private int tIdx;

    private String userId;

    private LocalDate tDate;

    private LocalTime tStartT;

    private LocalTime tEndT;

    private int tPeople;

    private int tType;

    public TimetableDto(LocalTime tStartT, LocalTime tEndT, int tPeople, int tType) {
        this.tStartT = tStartT;
        this.tEndT = tEndT;
        this.tPeople = tPeople;
        this.tType = tType;
    }
}
