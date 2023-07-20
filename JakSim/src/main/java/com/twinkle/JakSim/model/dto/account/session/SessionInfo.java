package com.twinkle.JakSim.model.dto.account.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfo {
    private String sessionId;
    private Principal principal;
    private Date lastRequest;
    private boolean expired;
}
