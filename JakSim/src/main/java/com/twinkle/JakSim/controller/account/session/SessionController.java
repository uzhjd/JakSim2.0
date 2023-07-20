package com.twinkle.JakSim.controller.account.session;

import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.dto.account.session.SessionInfo;
import com.twinkle.JakSim.model.dto.account.session.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SessionController {
    private final SessionRegistry registry;

    @GetMapping("/session/list")
    public String sessions(Model model){
        List<UserSession> userSessions = registry.getAllPrincipals().stream()
                .map(p -> UserSession.builder()
                        .username(((User)p).getUsername())
                        .sessions(registry.getAllSessions(p,false)
                                .stream().map(si ->SessionInfo.builder()
                                        .lastRequest(dateToLocalDateTime(si.getLastRequest()))
                                        .sessionId(si.getSessionId())
                                        .build()
                                ).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        model.addAttribute("sessionList", userSessions);
        return "content/account/session/sessionList";
    }

    private LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @PostMapping("/session/expire")
    public String expireSession(@RequestBody Map<String, String> sessionId){
        SessionInformation session = registry.getSessionInformation(sessionId.get("sessionId"));
        if(!session.isExpired()){
            session.expireNow();
        }
        return "redirect:/session-list";
    }

    @GetMapping("/session/expired")
    public String sessionExpired(){
        return "content/account/session/sessionExpired";
    }
}
