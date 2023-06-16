package com.twinkle.JakSim.model.dto.timetable.response;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class TimetableDto {
    private int tIdx;
    private int utIdx;
    private String tStartT;
    private String tEndT;
    private int tPeople;
    private int tType;
}
