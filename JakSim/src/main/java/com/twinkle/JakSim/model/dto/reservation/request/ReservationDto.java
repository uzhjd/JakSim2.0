package com.twinkle.JakSim.model.dto.reservation.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class ReservationDto {

    @NotBlank
    @Size(max = 30)
    private String userId;

    @NotBlank
    @Size(max = 8)
    private int pIdx;

    @NotBlank
    @Size(max = 8)
    private int tIdx;

    @NotBlank
    private LocalDate tDate;

//    이것도 필요가 없을 듯 => 현재 날짜 저장이자넹!! => 필요없을 줄 알았지만
//    현날짜 보다 이전 날짜가 들어온 것을 방지하기 위해 일단 막아주기 위해 받아는 옴
    @NotBlank
    private LocalDate rCDt;
}