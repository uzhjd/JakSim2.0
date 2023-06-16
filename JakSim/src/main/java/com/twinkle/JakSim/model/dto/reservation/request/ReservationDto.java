package com.twinkle.JakSim.model.dto.reservation.request;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
public class ReservationDto {
    @NotBlank
    @Size(max = 15)
    private String userId;

    @NotBlank
    private int utIdx;

    @NotBlank
    private int pIdx;

    @NotBlank
    private int tIdx;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rCDt;
}