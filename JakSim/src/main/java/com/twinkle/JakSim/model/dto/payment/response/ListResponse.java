package com.twinkle.JakSim.model.dto.payment.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ListResponse {
    String tid;
    String status;
    String payment_method_type;
    CanceledAmount amount;
    CancelAvailableAmountResponse cancel_available_amount;
    String item_name;
    String item_code;
    LocalDateTime created_at;
    LocalDateTime approved_at;
//    SelectedCardInfo selecte_card_info;
//    PaymentActionDetails[] payment_action_details;
}
