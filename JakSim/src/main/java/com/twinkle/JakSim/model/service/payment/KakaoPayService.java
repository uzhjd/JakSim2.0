package com.twinkle.JakSim.model.service.payment;

import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.dto.payment.response.CancelResponse;
import com.twinkle.JakSim.model.dto.payment.response.ReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {
    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
//    static final String admin_Key = "${ADMIN_KEY}"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private ReadyResponse kakaoReady;

    public ReadyResponse kakaoPayReady() {
//    public ReadyResponse kakaoPayReady(String tpIdx, int ptPrice) {

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
//        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_order_id", "1234");
//        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("partner_user_id", "gorany");
//        parameters.add("item_name", "상품명");
        parameters.add("item_name", "갤럭시 s9");
//        parameters.add("item_name", tpIdx); // "상품명"
        parameters.add("quantity", "1");
//        parameters.add("total_amount", "총 금액");
//        parameters.add("total_amount", String.valueOf(ptPrice)); // "총 금액");
        parameters.add("total_amount", "2100");
        parameters.add("vat_amount", "0"); // "부가세");
//        parameters.add("tax_free_amount", "상품 비과세 금액");
        parameters.add("tax_free_amount", "0"); // "상품 비과세 금액");
        parameters.add("approval_url", "http://localhost:8080/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8080/payment/fail"); // 실패 시 redirect url

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                ReadyResponse.class);


        return kakaoReady;
    }

    /**
     * 결제 환불
     */
    public CancelResponse kakaoCancel() {

        // 카카오페이 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", "환불할 결제 고유 번호");
        parameters.add("cancel_amount", "환불 금액");
        parameters.add("cancel_tax_free_amount", "환불 비과세 금액");
        parameters.add("cancel_vat_amount", "환불 부가세");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        CancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                CancelResponse.class);

        return cancelResponse;
    }
    /**
     * 결제 완료 승인
     */
    public ApproveResponse approveResponse(String pgToken) {

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        ApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                ApproveResponse.class);

        return approveResponse;
    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}
