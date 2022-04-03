package com.paypal.braintreeintegration.pojo;

public class PPResponse {
    private String scope;
    private String access_token;
    private String token_type;
    private String app_id;
    private String expires_in;
    private String nonce;

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getApp_id() {
        return this.app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getNonce() {
        return this.nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        String response = "Values[ scope: " + this.scope
                + ", access_token: " + this.access_token
                + ", token_type: " + this.token_type
                + ", app_id: " + this.app_id
                + ", expires_in: " + this.expires_in
                + ", nonce: " + this.nonce
                + "]";

        return response;
    }

}
