package com.twinkle.JakSim.model.service.inbody;

import com.twinkle.JakSim.model.dao.inbody.InbodyDao;
import com.twinkle.JakSim.model.dto.inbody.InbodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InbodyService {
    private final InbodyDao inbodyDao;

    public List<InbodyDto> getInbodies(String username) {
        return inbodyDao.getInbodiesAsc(username);
    }

    public List<InbodyDto> getInbodiesByPage(String username, int page){
        int pageSize = 10;
        return inbodyDao.getInbodiesByPages(username, page, pageSize);
    }

}
