package com.twinkle.JakSim.controller.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.IsReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@AuthenticationPrincipal User user,
                                                        @Valid @RequestBody ReservationRequest reservationRequest) {
        int response = 0;
        LocalDate today = LocalDate.now();

        if(reservationRequest.getDate().compareTo(today) < 0) {
            System.out.println("입력된 날이 더 과거입니다.");
        } else {
            response = reservationService.register(user.getUsername(), reservationRequest);
        }

        if(response == 0) {
            System.out.println("지난 날에 대한 예약은 할 수 없습니다.");
        } else if(response == 1) {
            System.out.println("이미 등록된 예약이 있습니다.");
        } else if (response == 2) {
            System.out.println("이미 등록된 예약이 있습니다.");
        } else if (response == 3) {
            System.out.println("해당 시간표는 존재하지 않습니다.");
        } else if (response == 4) {
            System.out.println("예약이 올바르게 되지 않았습니다.");
        } else {
            response = 5;
        }

        // 0: 지난 날, 1: 등록된 예약 O, 2: 사용가능한 PT권 X, 일정 존재 X, 4: 예기치 못한 에러 발생, 5: 예약 완료
        return ResponseEntity.ok().body(response);
    }

    // 5
    @PostMapping("/search")
    public ResponseEntity<ReservationResponse> reservation(@AuthenticationPrincipal User user,
                                                           @Valid @RequestBody IsReservationRequest resCheckRequest) {
        ReservationResponse response = reservationService.findReservation(user.getUsername(), resCheckRequest.getTrainerId(), resCheckRequest.getDt());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/cancle/{pIdx}/{rIdx}")
    public ResponseEntity<Boolean> resCancle(@AuthenticationPrincipal User user, @PathVariable("pIdx") int pIdx,
                              @PathVariable("rIdx") int rIdx) {
        Boolean response = reservationService.delete(pIdx, rIdx);

        return ResponseEntity.ok().body(response);
    }
}
