package com.hotbot.utils;

import org.springframework.http.HttpStatus;

public class ResponseErrorData {

    private String apiVerisonResponse;
    private HttpStatus httpStatusCode;
    private int responseStatus;
    private ErrorInfo errorInfo;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus ok) {
        this.httpStatusCode = ok;
    }

    public String getApiVerisonResponse() {
        return apiVerisonResponse;
    }

    public void setApiVerisonResponse(String apiVerisonResponse) {
        this.apiVerisonResponse = apiVerisonResponse;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "ResponseErrorData [apiVerisonResponse=" + apiVerisonResponse + ", httpStatusCode=" + httpStatusCode
                + ", errorInfo=" + errorInfo + "]";
    }

}
