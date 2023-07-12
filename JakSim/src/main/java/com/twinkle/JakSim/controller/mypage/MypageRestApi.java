package com.twinkle.JakSim.controller.mypage;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.inbody.InbodyDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import com.twinkle.JakSim.model.service.account.FileService;
import com.twinkle.JakSim.model.service.inbody.InbodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/mypage/api")
@RequiredArgsConstructor
public class MypageRestApi {
    private final AccountService accountService;
    private final FileService fileService;
    private final InbodyService inbodyService;
    @PostMapping("/auth")
    public String authPassword(@RequestBody UserDto userDto, @AuthenticationPrincipal User user){
        return accountService.checkPassword(user.getUsername(), userDto.getPw()) ? user.getUsername() : null;
    }
    @DeleteMapping("/delete")
    public int deleteUser(@AuthenticationPrincipal User user){
        return accountService.delete(user.getUsername());
    }
    @GetMapping("/sessiontime")
    public int getSessionTime(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            long currentTimeMills = System.currentTimeMillis();
            long lastAccessedTimeMills = session.getLastAccessedTime();
            int maxInactiveInterval = session.getMaxInactiveInterval();
            long elapsedTimeInSeconds = (currentTimeMills - lastAccessedTimeMills) / 1000;
            return maxInactiveInterval - (int) elapsedTimeInSeconds;
        }
        return 0;
    }

    @GetMapping("/user-info")
    public UserDetails getUserInfo(@AuthenticationPrincipal User user){
        return user;
    }

    @PutMapping("/profile/update")
    public boolean getData(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile data) throws Exception{
        return fileService.updateProfileImage(data, user.getUsername());
    }

    @PutMapping("/profile/update/email")
    public boolean changeEmail(@AuthenticationPrincipal User user, @RequestBody UserDto userDto){
        return accountService.updateEmail(userDto.getEmail(), user.getUsername());
    }

    @PostMapping("/email/get")
    public boolean dupEmail(@RequestBody UserDto userDto){
        return accountService.findByEmail(userDto.getEmail()) == null;
    }

    @PostMapping("/email/check")
    public String validEmail(@RequestBody UserDto userDto){
        return accountService.validateEmail(userDto.getEmail());
    }

    @PutMapping("/profile/update/name")
    public boolean changeName(@AuthenticationPrincipal User user, @RequestBody UserDto data){
        return accountService.updateName(data.getName(), user.getUsername());
    }

    @PutMapping("/profile/update/tel")
    public boolean changeTel(@AuthenticationPrincipal User user, @RequestBody UserDto data){
        return accountService.updateTel(data.getTel(), user.getUsername());
    }

    @PostMapping("/profile/check/tel")
    public boolean checkTel(@RequestBody UserDto data){
        return accountService.findByTel(data.getTel()) == null;
    }

    @GetMapping("/inbody/data")
    public List<InbodyDto> getInbody(@AuthenticationPrincipal User user){
        return inbodyService.getInbodies(user.getUsername());
    }

    @GetMapping("/inbody/totalpage")
    public int getTotalPage(@AuthenticationPrincipal User user){
        return inbodyService.getTotalPage(user.getUsername());
    }

    @GetMapping("/inbody/{page}")
    public List<InbodyDto> getInbodyByPage(@AuthenticationPrincipal User user, @PathVariable("page") int page){
        return inbodyService.getInbodiesByPage(user.getUsername(), page);
    }

    @PostMapping("/inbody/create")
    public int createInbodyData(@AuthenticationPrincipal User user, @RequestBody InbodyDto data){
        return inbodyService.create(user.getUsername(), data);
    }

    @DeleteMapping("/inbody/del/{id}")
    public int deleteInbodyData(@PathVariable("id") int id){
        return inbodyService.delete(id);
    }
}
