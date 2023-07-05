package com.twinkle.JakSim.controller.product;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final PaymentService paymentService;

    @GetMapping("/myPt")
    public ResponseEntity<List<ValidPtDto>> TrainerList(@AuthenticationPrincipal User user) {
        List<ValidPtDto> response = paymentService.findValidPtList(user.getUsername());

        return ResponseEntity.ok(response);
    }
}
