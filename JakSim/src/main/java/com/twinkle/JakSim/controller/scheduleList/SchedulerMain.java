package com.twinkle.JakSim.controller.scheduleList;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerMain {

    @GetMapping("")
    public String scheduler(@AuthenticationPrincipal User user) {
        String isUser = "ROLE_USER";

        for(GrantedAuthority authority : user.getAuthorities()){
            String authorityString = authority.getAuthority();

            if(authorityString.contains(isUser)){
                return "content/scheduleList/generalScheduleList";
            }
        }

        return "content/scheduleList/trainerScheduleList";
    }
}
