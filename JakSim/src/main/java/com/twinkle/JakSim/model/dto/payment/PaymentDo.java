package com.twinkle.JakSim.model.dto.payment;

import lombok.Data;

@Data
public class PaymentDo {
    private int idx;
    private String user_id;
    private int tp_idx;
    private String c_dt;
    private int refund;
    private String m_dt;
    private int cnt;
    private int period;
}
