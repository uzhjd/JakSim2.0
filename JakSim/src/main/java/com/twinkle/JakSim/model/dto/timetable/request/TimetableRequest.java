package com.twinkle.JakSim.model.dto.timetable.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class TimetableRequest {

    @NotBlank
    @Size(max = 30)
    private String trainerId;

    @NotBlank
    private LocalDate tDate;

    @NotBlank
    private int tType;
}
