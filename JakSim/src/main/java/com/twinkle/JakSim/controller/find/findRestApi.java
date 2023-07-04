package com.twinkle.JakSim.controller.find;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/find/api")
public class findRestApi {
    private final AccountService accountService;
    @PostMapping("/email")
    public boolean checkEmail(@RequestBody UserDto userDto){
        return accountService.findByEmail(userDto.getEmail()) != null;
    }

    @PostMapping("/email/get")
    public UserDto getEmail(@RequestBody UserDto userDto){
        return accountService.findByEmail(userDto.getEmail());
    }

    @PostMapping("/email/action")
    public String actionEmail(@RequestBody UserDto userDto){
        return accountService.validateEmail(userDto.getEmail());
    }
}
