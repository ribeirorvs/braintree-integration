package com.paypal.braintreeintegration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@SpringBootApplication
@RestController
public class CreatePayment {

    private final String url = "https://api-m.sandbox.paypal.com/v1/payments/payment";
    private HttpHeaders headers = new HttpHeaders();
    private String body;
    private HttpEntity<String> bodyAndHeaders;
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> result;
    private JsonNode node;

    @GetMapping("create-payment")
    public String GetCreatePayment(@RequestParam String token) {

        // Define post call headers and body
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        body = "{\n  \"intent\": \"sale\",\n  \"payer\": {\n    \"payment_method\": \"paypal\"\n  },\n  \"transactions\": [{\n    \"amount\": {\n      \"currency\": \"BRL\",\n      \"total\": \"93.00\",\n      \"details\": {\n        \"shipping\": \"11\",\n        \"subtotal\": \"75\",\n        \"shipping_discount\": \"1.00\",\n        \"insurance\": \"1.00\",\n        \"handling_fee\": \"1.00\",\n        \"tax\": \"6.00\"\n      }\n    },\n    \"description\": \"This is the payment transaction description\",\n    \"payment_options\": {\n      \"allowed_payment_method\": \"IMMEDIATE_PAY\"\n    },\n    \"item_list\": {\n      \"shipping_address\": {\n        \"recipient_name\": \"PP Plus Recipient\",\n        \"line1\": \"Gregório Rolim de Oliveira, 42\",\n        \"line2\": \"JD Serrano II\",\n        \"city\": \"Votorantim\",\n        \"country_code\": \"BR\",\n        \"postal_code\": \"18117-134\",\n        \"state\": \"São Paulo\",\n        \"phone\": \"0800-761-0880\"\n      },\n      \"items\": [{\n        \"name\": \"handbag\",\n        \"description\": \"red diamond\",\n        \"quantity\": \"1\",\n        \"price\": \"75\",\n        \"tax\": \"6\",\n        \"sku\": \"product34\",\n        \"currency\": \"BRL\"\n      }]\n    }\n  }],\n  \"redirect_urls\": {\n    \"return_url\": \"https://example.com/return\",\n    \"cancel_url\": \"https://example.com/cancel\"\n  }\n}";

        bodyAndHeaders = new HttpEntity<>(body, headers);

        // POST call
        result = restTemplate.exchange(url, HttpMethod.POST, bodyAndHeaders, String.class);

        try {
            // Convert the json response in nodes and take only the approval url string
            node = new ObjectMapper().readTree(result.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return node.get("links").toString();
    }

}
