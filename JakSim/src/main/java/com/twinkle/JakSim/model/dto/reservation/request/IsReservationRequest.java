package com.twinkle.JakSim.model.dto.reservation.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class IsReservationRequest {

    @NotBlank
    private String dt;

    @NotBlank
    private String trainerId;
}
