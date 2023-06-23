package com.twinkle.JakSim.model.dto.payment.response;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private int pIdx;

    private int pPtCnt;
}
