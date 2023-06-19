package com.twinkle.JakSim.model.dto.trainer;

import lombok.Data;

@Data
public class ProductDto {
    private int ptId;
    private String userId;
    private int ptTimes;
    private int ptPrice;
    private int ptType; // 0:개인 1:단체
    private String ptTitle;
    private int ptPeriod; //만료를 위한 PT 기간

    //private String ptContent;
}
