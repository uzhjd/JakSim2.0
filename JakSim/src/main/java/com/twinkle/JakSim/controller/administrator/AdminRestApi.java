package com.twinkle.JakSim.controller.administrator;

import com.twinkle.JakSim.model.dto.account.LoginLogStat;
import com.twinkle.JakSim.model.service.account.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/man/api")
public class AdminRestApi {
    private final LoginLogService loginLogService;
    @GetMapping("/visit/all")
    public int getAllAccess(){
        return loginLogService.getAllAccess();
    }

    @GetMapping("/visit/date")
    public List<LoginLogStat> getAmountDateAllUsers(@RequestParam(required = false) String start, @RequestParam(required = false) String end){
        return loginLogService.getAmountDate(start, end);
    }

    @GetMapping("/visit/user")
    public List<LoginLogStat> getAmountDateSingleUsers(@RequestParam String username, @RequestParam(required = false)String start, @RequestParam(required = false)String end){
        return loginLogService.getAmountSingleUser(username, start, end);
    }

    @GetMapping("/visit/group-user")
    public List<LoginLogStat> getAmountDateGroupUsers(@RequestParam(required = false) String start, @RequestParam(required = false) String end, @RequestParam(required = false) boolean order){
        return loginLogService.getAmountGroupUser(start, end, order);
    }
}
