package com.hotbot.exception;

public class ErrorDetails {

    private int errorCode;
    private String errorModuleType;
    private String message;
    private String apiVersion;

    public ErrorDetails(String errorModuleType, int errorCode, String message) {

        this.errorModuleType = errorModuleType;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorDetails(String apiVersion, String errorModuleType, int errorCode, String message) {

        this.errorModuleType = errorModuleType;
        this.errorCode = errorCode;
        this.message = message;
        this.apiVersion = apiVersion;

    }

    public ErrorDetails() {

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorModuleType() {
        return errorModuleType;
    }

    public void setErrorModuleType(String errorModuleType) {
        this.errorModuleType = errorModuleType;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }


}
