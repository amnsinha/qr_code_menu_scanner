package com.hotbot.exception;

import com.hotbot.utils.BasicConstants;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseErrorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class CureFullExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CureFullExceptionHandler.class);


    @ExceptionHandler(value = {CureFullException.class})
    @ResponseBody
    public ResponseEntity<ResponseErrorData> handleCureFulExceptionAndSendException(CureFullException cureFullException,
                                                                                    HttpServletRequest request) throws IOException {

        LOGGER.info("Exception details {}", cureFullException);

        //String accessToken = request.getHeader(BasicConstants.USER_ACCESS_TOKEN);
        //UserLoginTokenModel userLoginTokenModel = userLoginTokenService.findByAccessToken(accessToken);


		/*Long apiDetailsLogIdBefore = (Long) request.getAttribute("apiDetailsLogId");

		ApiDetailsLogModel apiDetailsLogModel = apiDetailsLogRepository.findOne(apiDetailsLogIdBefore);

		ApiErrorDetailsModel apiErrorDetailsModel = new ApiErrorDetailsModel();

		apiErrorDetailsModel.setApiVersion(cureFullException.getErrorDetails().getApiVersion());
		apiErrorDetailsModel.setErrorCode(cureFullException.getErrorDetails().getErrorCode());
		apiErrorDetailsModel.setErrorType(cureFullException.getErrorType());
		apiErrorDetailsModel.setIpAddress(request.getRemoteAddr());
		apiErrorDetailsModel.setRequestParam("Request Param Pending");
		apiErrorDetailsModel.setRequestUrl(request.getRequestURL().toString());
		apiErrorDetailsModel.setTimestamp(LocalDateTime.now());
		apiErrorDetailsModel.setRequestParam(apiDetailsLogModel.getRequestParam());

		apiErrorDetailsModel.setUserId(apiDetailsLogModel.getUserId());

		//Throwable throwable = cureFullException.getCause();

		//Converting stack trace to string
		StringWriter sw = new StringWriter();
		@SuppressWarnings("unused")
		PrintWriter pw = new PrintWriter(sw);
		apiErrorDetailsModel.setExceptionStackTrace( sw.toString());

		apiErrorDetailsService.saveApiErrorDetailsModel(apiErrorDetailsModel);*/


        //schedularManager.logTheException(cureFullException, logRequestResponseInfo);

        return ResponseBuilder.getErrorResponse(cureFullException);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(value = {Throwable.class})
    @ResponseBody
    public ResponseEntity<ResponseErrorData> handleCureFulExceptionAndSendException(Throwable throwable,
                                                                                    HttpServletRequest request) throws IOException {

        //StringWriter errors = new StringWriter();

        String msg = throwable.getMessage();

        Throwable cause = throwable.getCause();

        Throwable[] supressed = throwable.getSuppressed();

        StackTraceElement[] stackTrace = throwable.getStackTrace();

        String onWhichLine = stackTrace[0].toString();

        LOGGER.info("Exception details {}", throwable);

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setErrorCode(BasicConstants.INTERNEL_SERVER_ERROR_CODE);

        if (msg != null) {

            errorDetails.setMessage(msg);

        } else {

            errorDetails.setMessage("Null Pointer Exception");

        }

        errorDetails.setErrorModuleType(BasicConstants.INTERNEL_SERVER_ERROR);

        CureFullException cureFullException = new CureFullException();

        cureFullException.setErrorDetails(errorDetails);
        cureFullException.setErrorType(BasicConstants.ERROR_TYPE_ERROR);
        cureFullException.setErrorTypeCode(BasicConstants.ERROR_TYPE_CODE_ERROR);

        //LogRequestResponseInfo logRequestResponseInfo = new LogRequestResponseInfo();

        //schedularManager.logTheException(cureFullException, logRequestResponseInfo);
		/*String accessToken = request.getHeader(BasicConstants.USER_ACCESS_TOKEN);
		UserLoginTokenModel userLoginTokenModel = userLoginTokenService.findByAccessToken(accessToken);

		Long apiDetailsLogIdBefore = (Long) request.getAttribute("apiDetailsLogId");

		ApiDetailsLogModel apiDetailsLogModel = apiDetailsLogRepository.findOne(apiDetailsLogIdBefore);

		ApiErrorDetailsModel apiErrorDetailsModel = new ApiErrorDetailsModel();

		apiErrorDetailsModel.setApiVersion(cureFullException.getErrorDetails().getApiVersion());
		apiErrorDetailsModel.setErrorCode(cureFullException.getErrorDetails().getErrorCode());
		apiErrorDetailsModel.setErrorType(cureFullException.getErrorType());
		apiErrorDetailsModel.setIpAddress(request.getRemoteAddr());
		apiErrorDetailsModel.setRequestParam("Request Param Pending");
		apiErrorDetailsModel.setRequestUrl(request.getRequestURL().toString());
		apiErrorDetailsModel.setTimestamp(LocalDateTime.now());
		apiErrorDetailsModel.setUserId(apiDetailsLogModel.getUserId());
		apiErrorDetailsModel.setRequestParam(apiDetailsLogModel.getRequestParam());
		//apiErrorDetailsModel.setExceptionStackTrace(cureFullException.getErrorDetails().getMessage());

		//Converting stack trace to string
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);

		apiErrorDetailsModel.setExceptionStackTrace( sw.toString());

		apiErrorDetailsService.saveApiErrorDetailsModel(apiErrorDetailsModel);*/

        return ResponseBuilder.getErrorResponse(cureFullException);

    }


}
