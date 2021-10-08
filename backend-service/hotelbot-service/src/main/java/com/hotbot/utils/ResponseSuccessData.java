package com.hotbot.utils;

import org.springframework.http.HttpStatus;

public class ResponseSuccessData {

    private String apiVerisonResponse;
    private HttpStatus httpStatusCode;
    private int responseStatus;
    private Object payload;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getApiVerisonResponse() {
        return apiVerisonResponse;
    }

    public void setApiVerisonResponse(String apiVerisonResponse) {
        this.apiVerisonResponse = apiVerisonResponse;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ResponseSuccessData [apiVerisonResponse=" + apiVerisonResponse + ", httpStatusCode=" + httpStatusCode
                + ", responseStatus=" + responseStatus + ", payload=" + payload + "]";
    }

}
