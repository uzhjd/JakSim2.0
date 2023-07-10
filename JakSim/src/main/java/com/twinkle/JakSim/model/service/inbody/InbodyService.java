package com.twinkle.JakSim.model.service.inbody;

import com.twinkle.JakSim.model.dao.inbody.InbodyDao;
import com.twinkle.JakSim.model.dto.inbody.InbodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InbodyService {
    private final InbodyDao inbodyDao;

    public List<InbodyDto> getInbodies(String username) {
        return inbodyDao.getInbodies(username);
    }
}
