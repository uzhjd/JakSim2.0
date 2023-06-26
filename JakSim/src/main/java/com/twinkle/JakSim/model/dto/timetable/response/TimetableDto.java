package com.twinkle.JakSim.model.dto.timetable.response;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@ToString
public class TimetableDto {
    private int tIdx;

    private String userId;

    private LocalDate tDate;

    private LocalTime tStartT;

    private LocalTime tEndT;

    private int tPeople;

    private int tType;
}
