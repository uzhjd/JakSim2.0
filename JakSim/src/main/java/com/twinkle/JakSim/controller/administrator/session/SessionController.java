package com.twinkle.JakSim.controller.administrator.session;

import com.twinkle.JakSim.model.dto.administrator.session.SessionInfo;
import com.twinkle.JakSim.model.dto.administrator.session.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SessionController {
    private final SessionRegistry registry;
    private final String defaultPath = "content/administrator/session/";

    @GetMapping("/session/list")
    public String sessions(Model model) {
        List<UserSession> userSessions = registry.getAllPrincipals().stream()
                .map(p -> UserSession.builder()
                        .username(((User) p).getUsername())
                        .sessions(registry.getAllSessions(p, false)
                                .stream().map(si -> SessionInfo.builder()
                                        .lastRequest(dateToLocalDateTime(si.getLastRequest()))
                                        .sessionId(si.getSessionId())
                                        .build()
                                ).collect(Collectors.toList()))
                        .build()).sorted(new Comparator<UserSession>() {
                    @Override
                    public int compare(UserSession o1, UserSession o2) {
                        return o2.getSessions().get(0).getLastRequest().compareTo(o1.getSessions().get(0).getLastRequest());
                    }
                }).collect(Collectors.toList());

        model.addAttribute("sessionList", userSessions);
        model.addAttribute("amount_user", userSessions.size());

        return defaultPath + "sessionList";
    }

    private LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @PostMapping("/session/expire")
    public String expireSession(@RequestBody Map<String, String> sessionId) {
        SessionInformation session = registry.getSessionInformation(sessionId.get("sessionId"));
        if (!session.isExpired()) {
            session.expireNow();
        }
        return "redirect:/session-list";
    }

    @GetMapping("/session/expired")
    public String sessionExpired() {
        return defaultPath + "sessionExpired";
    }
}
