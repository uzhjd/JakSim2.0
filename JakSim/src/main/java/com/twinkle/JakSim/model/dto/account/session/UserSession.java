package com.twinkle.JakSim.model.dto.account.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    private String username;
    private List<SessionInfo> sessions;
    public int getCount(){
        return sessions.size();
    }
}
