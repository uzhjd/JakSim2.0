package com.twinkle.JakSim.model.dto.timetable.response;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TimetableDto {
    private int tIdx;

    private int utIdx;

    private LocalDate tDate;

    private LocalTime tStartT;

    private LocalTime tEndT;

    private int tPeople;

    private int tType;
}
