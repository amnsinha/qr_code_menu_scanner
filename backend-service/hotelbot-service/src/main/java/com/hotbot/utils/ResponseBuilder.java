package com.hotbot.utils;

import com.hotbot.exception.CureFullException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static ResponseEntity<ResponseErrorData> getErrorResponse(CureFullException cureFullException) {

        ResponseErrorData responseData = new ResponseErrorData();

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setErrorDetails(cureFullException.getErrorDetails());
        errorInfo.setErrorType(cureFullException.getErrorType());
        errorInfo.setErrorTypeCode(cureFullException.getErrorTypeCode());

        responseData.setApiVerisonResponse(BasicConstants.API_VERSION);
        responseData.setHttpStatusCode(HttpStatus.OK);
        responseData.setResponseStatus(BasicConstants.ERROR_INFO);
        responseData.setErrorInfo(errorInfo);

        ResponseEntity<ResponseErrorData> responseEntity = new ResponseEntity<ResponseErrorData>(responseData,
                HttpStatus.OK);

        return responseEntity;

    }

    public static ResponseEntity<ResponseSuccessData> getSuccessResponse(Object response) {

        ResponseSuccessData responseSuccessData = new ResponseSuccessData();

        responseSuccessData.setApiVerisonResponse(BasicConstants.API_VERSION);
        responseSuccessData.setHttpStatusCode(HttpStatus.OK);
        responseSuccessData.setPayload(response);

        responseSuccessData.setResponseStatus(BasicConstants.PAYLOAD);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseSuccessData);


    }

    public static ResponseEntity<ResponseSuccessData> getSuccessResponse(Object response, HttpHeaders httpHeaders) {

        ResponseSuccessData responseSuccessData = new ResponseSuccessData();

        responseSuccessData.setApiVerisonResponse(BasicConstants.API_VERSION);
        responseSuccessData.setHttpStatusCode(HttpStatus.OK);
        responseSuccessData.setPayload(response);
        responseSuccessData.setResponseStatus(BasicConstants.PAYLOAD);
        ResponseEntity<ResponseSuccessData> responseEntity = new ResponseEntity<ResponseSuccessData>(
                responseSuccessData, httpHeaders, HttpStatus.OK);

        return responseEntity;

    }

    public static ResponseEntity<ResponseSuccessData> getSuccessResponse() {

        ResponseSuccessData responseSuccessData = new ResponseSuccessData();

        responseSuccessData.setApiVerisonResponse(BasicConstants.API_VERSION);
        responseSuccessData.setHttpStatusCode(HttpStatus.OK);
        responseSuccessData.setPayload("No Data");
        responseSuccessData.setResponseStatus(BasicConstants.PAYLOAD);

        ResponseEntity<ResponseSuccessData> responseEntity = new ResponseEntity<ResponseSuccessData>(
                responseSuccessData, HttpStatus.OK);

        return responseEntity;
    }

}
