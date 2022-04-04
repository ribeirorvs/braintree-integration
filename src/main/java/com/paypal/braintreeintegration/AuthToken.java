package com.paypal.braintreeintegration;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.paypal.braintreeintegration.pojo.PPResponse;

@SpringBootApplication
@RestController
public class AuthToken {

    private RestTemplate restTemplate = new RestTemplate();
    private String url;
    private HttpHeaders headers = new HttpHeaders();
    private List<MediaType> mediaType = new ArrayList<MediaType>();
    private String simpleBody;
    private HttpEntity<String> bodyAndHeaders;
    private String clientId;
    private String secret;
    private final ObjectMapper objectMapper = new ObjectMapper();
    PPResponse aToken;

    @GetMapping("/aToken")
    public String AToken() {

        // Define the API request details
        url = "https://api-m.sandbox.paypal.com/v1/oauth2/token";
        clientId = "AfNUoefz-HDlfM6R65On7kiXhARQ6WLXC2aXdECEvU-DZXKW7dVtAuop-Xyh4FDh-dASnMwUXcBnTIpq";
        secret = "EMO0dUQne4dDa7ij0X3KirsSmfTH-90yzx0jZgV4yVvtaWbnwgEnnorMugOipYB30PilA2I1jlCJ3NIl";
        mediaType.add(MediaType.APPLICATION_JSON);
        simpleBody = "grant_type=client_credentials";

        // Define the Headers
        headers.setAccept(mediaType);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, secret);
        bodyAndHeaders = new HttpEntity<>(simpleBody, headers);

        // Make the request
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, bodyAndHeaders, String.class);

        // Create a response from return
        try {
            aToken = objectMapper.readValue(result.getBody(), PPResponse.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return aToken.getAccess_token();
    }
}
