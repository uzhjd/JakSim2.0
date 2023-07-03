package com.twinkle.JakSim.model.dto.timetable.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
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
}
