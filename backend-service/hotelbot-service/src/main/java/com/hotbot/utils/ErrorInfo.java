package com.hotbot.utils;


import com.hotbot.exception.ErrorDetails;

public class ErrorInfo {

    private String errorType;
    private int errorTypeCode;
    private ErrorDetails errorDetails;

    public ErrorInfo(String errorType, int errorTypeCode, ErrorDetails errorDetalis) {

        this.errorDetails = errorDetalis;
        this.errorTypeCode = errorTypeCode;
        this.errorType = errorType;
    }

    public ErrorInfo() {

    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public int getErrorTypeCode() {
        return errorTypeCode;
    }

    public void setErrorTypeCode(int errorTypeCode) {
        this.errorTypeCode = errorTypeCode;
    }

    @Override
    public String toString() {
        return "ErrorInfo [errorType=" + errorType + ", errorTypeCode=" + errorTypeCode + ", errorDetails="
                + errorDetails + "]";
    }

}
