//package com.twinkle.JakSim.controller.scheduleList;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//@RequestMapping("/scheduler")
//@RequiredArgsConstructor
//public class SchedulerMain {
//
//    @GetMapping()
//    public String generalScheduler(@AuthenticationPrincipal User user) {
//
//
//
//        if(user.getAuthorities().equals("ROLE_USER")) {
//            return "content/scheduleList/generalScheduleList";
//        }
//
//        return "content/scheduleList/trainerScheduleList";
//    }
//}
