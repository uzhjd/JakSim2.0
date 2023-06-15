package com.twinkle.JakSim.model.dto.reservation;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class ReservationDto {
    @NotBlank
    private String userId;

    @NotBlank
    private int utIdx;

    @NotBlank
    private int pIdx;

    @NotBlank
    private int tIdx;

    @NotBlank
    private String rCDt;
}