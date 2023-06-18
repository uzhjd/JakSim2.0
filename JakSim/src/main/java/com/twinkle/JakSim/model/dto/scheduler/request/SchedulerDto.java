package com.twinkle.JakSim.model.dto.scheduler.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
public class SchedulerDto {
    @NotBlank
    @Size(max = 15)
    private String userId;


}
