package com.twinkle.JakSim.model.dto.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerCertDto {
    private int certId;
    private String userId;
    private String certName;
    private String certImage;

}
