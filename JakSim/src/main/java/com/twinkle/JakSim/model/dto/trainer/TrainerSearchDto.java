package com.twinkle.JakSim.model.dto.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerSearchDto {

    private String userName;

    private String insta;
    private String introduce;
    private String gym;
    private int expert1;
    private int expert2;

    private int ptTimes;
    private int ptPrice;
    private String ptTitle;

    private String careerContent;

    private String certName;
    private String certImage;

    private String imagePath;

}
