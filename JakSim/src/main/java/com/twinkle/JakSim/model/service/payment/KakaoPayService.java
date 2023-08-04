package com.twinkle.JakSim.model.service.payment;

import com.twinkle.JakSim.model.dto.payment.request.PaymentRequest;
import com.twinkle.JakSim.model.dto.payment.request.RefundRequest;
import com.twinkle.JakSim.model.dto.payment.response.ApproveResponse;
import com.twinkle.JakSim.model.dto.payment.response.CancelResponse;
import com.twinkle.JakSim.model.dto.payment.response.ListResponse;
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

    static final String cid = "TC0ONETIME";
    static final String admin_Key = "04b96e410ef97e9ba8dfe96ea57746bf";
    private ReadyResponse kakaoReady;

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    public ReadyResponse kakaoPayReady(String userId, PaymentRequest paymentRequest) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("partner_order_id", "1234");
        parameters.add("partner_user_id", userId);
        parameters.add("item_name", String.valueOf(paymentRequest.getPtTitle()));
        parameters.add("item_code", String.valueOf(paymentRequest.getTpIdx()));
        parameters.add("quantity", "1");
        parameters.add("total_amount", String.valueOf(paymentRequest.getPtPrice()));
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/payment/success");
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel");
        parameters.add("fail_url", "http://localhost:8080/payment/fail");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        kakaoReady = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/ready",
                                                                                requestEntity, ReadyResponse.class);
        kakaoReady.setPtTimes(paymentRequest.getPtTimes());
        kakaoReady.setPtPeriod(paymentRequest.getPtPeriod());

        return kakaoReady;
    }

    // 결제 완료 승인
    public ApproveResponse approveResponse(String userId, String pgToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "1234");
        parameters.add("partner_user_id", userId);
        parameters.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        ApproveResponse approveResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/approve",
                                                                                requestEntity, ApproveResponse.class);

        approveResponse.setPtTimes(kakaoReady.getPtTimes());
        approveResponse.setPtPeriod(kakaoReady.getPtPeriod());

        return approveResponse;
    }

    // 결제 환불
    public CancelResponse kakaoCancel(RefundRequest refundRequest) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("tid", refundRequest.getTid());
        parameters.add("cancel_amount", String.valueOf(refundRequest.getCancel_amount()));
        parameters.add("cancel_tax_free_amount", "0");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        CancelResponse cancelResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/cancel",
                                                                                requestEntity, CancelResponse.class);

        return cancelResponse;
    }

    public ListResponse kakaoList(String tid) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("tid", String.valueOf(tid));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        ListResponse listResponse = restTemplate.postForObject("https://kapi.kakao.com/v1/payment/order",
                                                                                    requestEntity, ListResponse.class);

        return listResponse;
    }
}
