package com.paypal.braintreeintegration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@RestController
public class ExecutePayment {

    private String url;
    private HttpHeaders headers = new HttpHeaders();
    private String body;
    private HttpEntity<String> bodyAndHeaders;
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> result;

    @GetMapping("/execute-payment")
    public void GetExecutePayment(@RequestParam String paymentId, @RequestParam String payerId,
            @RequestParam String token) {

        // Define URL, headers, and body
        url = paymentId;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        body = String.format("{\n \"payer_id\": \"%s\" \n}", payerId);

        bodyAndHeaders = new HttpEntity<>(body, headers);

        result = restTemplate.exchange(url, HttpMethod.POST, bodyAndHeaders, String.class);

    }

}
