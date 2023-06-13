package com.twinkle.JakSim.model.dto;

import lombok.Data;

@Data
public class TimetableDto {
    private int tIdx;
    private int utIdx;
    private String tStartT;
    private String tEndT;
    private int tPeople;
    private int tType;
}
