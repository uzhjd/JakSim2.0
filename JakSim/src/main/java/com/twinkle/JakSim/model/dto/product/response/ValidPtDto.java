package com.twinkle.JakSim.model.dto.product.response;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class ValidPtDto {

    private String trainerId;

    private int pIdx;

    private int pPtCnt;
}