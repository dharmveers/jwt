package com.auth.jwt.responses;

public class AuthorizationResponse {
    private final Integer statusCode;
    private final String statusMessage;
    private final String timeStamp;

    public AuthorizationResponse(Integer statusCode, String statusMessage, String timeStamp) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "{" +
                "\n statusCode : " + statusCode +
                "\n statusMessage : " + statusMessage+
                "\n timeStamp : " + timeStamp +"\n"+
                '}';
    }
}
