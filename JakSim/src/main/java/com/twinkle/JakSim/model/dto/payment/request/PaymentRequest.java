package com.twinkle.JakSim.model.dto.payment.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class PaymentRequest {

    @NotEmpty
    @Max(10)
    // 가맹점 코드 (10자)
    private String cid;

    // 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합
    private String cid_secret;

    // 가맹점 주문번호, 최대 100자
    @NotEmpty
    private String partner_order_id;

    //가맹점 회원 id, 최대 100자
    @NotEmpty
    private String partner_user_id;

    //상품명, 최대 100자
    @NotEmpty
    private String item_name;

    //상품코드, 최대 100자
    private String item_code;

    //상품 수량
    @NotEmpty
    private int quantity;

    //상품 총액
    @NotEmpty
    private int total_amount;

    //	상품 비과세 금액
    @NotEmpty
    private int tax_free_amount;

    //	상품 부가세 금액
    //값을 보내지 않을 경우 다음과 같이 VAT 자동 계산
    //(상품총액 - 상품 비과세 금액)/11 : 소숫점 이하 반올림
    private int vat_amount;

    //컵 보증금
    private int green_deposit;

    //결제 성공 시 redirect url, 최대 255자
    @NotEmpty
    private int approval_url;

    //결제 실패 시 redirect url, 최대 255자
    @NotEmpty
    private String cancel_url;

    //결제 수단으로써 사용 허가할 카드사를 지정해야 하는 경우 사용, 카카오페이와 사전 협의 필요
    //사용 허가할 카드사 코드*의 배열
    //ex) ["HANA", "BC"]
    //(기본값: 모든 카드사 허용)
    @NotEmpty
    private String fail_url;

    // 	사용 허가할 결제 수단, 지정하지 않으면 모든 결제 수단 허용
    //CARD 또는 MONEY 중 하나

    // 원래는 jsonArray타입인데 임의로 바꾼 것이다.
    private List available_cards;

    //	카드 할부개월, 0~12
    private String payment_method_type;

    //
    private int install_month;

    //결제 화면에 보여줄 사용자 정의 문구, 카카오페이와 사전 협의 필요
    //ex) iOS에서 사용자 인증 완료 후 가맹점 앱으로 자동 전환하는 방법(iOS만 예외 처리, 안드로이드 동작 안 함)
    //- 다음과 같이 return_custom_url key 정보에 앱스킴을 넣어서 전송
    //return_custom_url":"kakaotalk://
    private Map<String, String> custom_json;
}
