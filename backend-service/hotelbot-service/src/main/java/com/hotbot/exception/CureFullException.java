package com.hotbot.exception;

public class CureFullException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorType;
    private int errorTypeCode;
    private ErrorDetails errorDetails;

    public CureFullException(String errorType, int errorTypeCode, ErrorDetails errorDetalis) {

        super(errorDetalis.getMessage());

        this.errorDetails = errorDetalis;
        this.errorTypeCode = errorTypeCode;
        this.errorType = errorType;
    }

    public CureFullException() {

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
        return "CureFullException [errorType=" + errorType + ", errorTypeCode=" + errorTypeCode + ", errorDetails="
                + errorDetails + "]";
    }

}
