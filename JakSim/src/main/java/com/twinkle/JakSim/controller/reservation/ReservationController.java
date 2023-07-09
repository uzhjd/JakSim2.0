package com.twinkle.JakSim.controller.reservation;

import com.twinkle.JakSim.model.dto.reservation.request.IsReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.request.ReservationRequest;
import com.twinkle.JakSim.model.dto.reservation.response.ReservationResponse;
import com.twinkle.JakSim.model.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@AuthenticationPrincipal User user,
                                        @Valid @RequestBody ReservationRequest reservationRequest) throws Exception {
        int response = 0;
        LocalDate today = LocalDate.now();

        if(reservationRequest.getTDate().compareTo(today) >= 0) {
            System.out.println("입력된 날이 더 과거입니다.");
            response = reservationService.register(user.getUsername(), reservationRequest);

            if(response == 1) {
                throw new Exception("이미 등록된 예약이 있습니다.");
            } else if (response == 2) {
                throw new Exception("PT권이 모두 사용되었습니다.");
            } else if (response == 3) {
                throw new Exception("해당 일정은 존재하지 않습니다.");
            } else if (response == 4) {
                throw new Exception("예약이 올바르게 되지 않았습니다.");
            }
        }

        return ResponseEntity.ok().body(response);
    }

    // 5
    @PostMapping("/search")
    public ResponseEntity<ReservationResponse> reservation(@AuthenticationPrincipal User user,
                                                           @Valid @RequestBody IsReservationRequest resCheckRequest) {

        ReservationResponse response = reservationService.findReservation(user.getUsername(), resCheckRequest.getTrainerId(), LocalDate.parse(resCheckRequest.getDt()));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/cancle/{pIdx}/{rIdx}/")
    public ResponseEntity<Boolean> resCancle(@AuthenticationPrincipal User user, @PathVariable("rIdx") int rIdx,
                              @PathVariable("pIdx") int pIdx) {
        Boolean response = reservationService.delete(pIdx, rIdx);

        return ResponseEntity.ok().body(response);
    }
}
