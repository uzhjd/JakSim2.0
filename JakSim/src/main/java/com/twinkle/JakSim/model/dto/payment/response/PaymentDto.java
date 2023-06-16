package com.twinkle.JakSim.model.dto.payment.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@ToString
public class PaymentDto {
    @NotBlank
    private int pIdx;

    @NotBlank
    private String userId;

    @NotBlank
    private int tpIdx;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pCDt;
    private int pRefund;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pMDt;

    @NotBlank
    private int pPtCnt;

}
