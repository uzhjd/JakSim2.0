package com.twinkle.JakSim.model.dto.scheduler.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SchedulerDto {
    @NotBlank
    @Size(max = 15)
    private String userId;


}
