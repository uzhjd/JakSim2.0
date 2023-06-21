package com.twinkle.JakSim.model.dto.trainer.response;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDetailDto {

    private int trainerIdX;

    private String userId;

    private String userName;

    private int gender;

    @Nullable
    private String insta;

    private String introduce;

    private String gym;

    private int expert1;

    private int expert2;
}
