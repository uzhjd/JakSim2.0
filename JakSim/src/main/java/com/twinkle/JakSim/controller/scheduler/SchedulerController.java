package com.twinkle.JakSim.controller.scheduler;

import com.twinkle.JakSim.model.dto.product.response.ValidPtDto;
import com.twinkle.JakSim.model.dto.scheduler.request.SchedulerDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerDto;
import com.twinkle.JakSim.model.service.payment.PaymentService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerController {

    @Autowired
    private DataSource ds;

    private TrainerService trainerService;
    private PaymentService paymentService;
    private TrainerDto trainerInfo;

    @PostMapping("/scheduler")
    public String Scheduler(@Valid @RequestBody SchedulerDto schedulerDto) {

        List<ValidPtDto> validPtList;

        try {
            validPtList = paymentService.validPtList(schedulerDto.getUserId());
        } catch (NullPointerException e) {
            System.out.println("There's no any valid Pt list");
            return "";
        }

        int ptCnt = validPtList.get(0).getPPtCnt();
        trainerInfo = trainerService.myTrainer(validPtList.get(0).getUtIdx());

        // 예약 현황


        return "";
    }
}
