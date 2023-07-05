package com.twinkle.JakSim.model.dto.reservation.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class ReservationCkRequest {

    private LocalDate tDate;

    @NotBlank
    private String trainerId;
}
