package com.service.api.exceptionsHandler;

import com.service.api.exceptions.DatabaseException;
import com.service.api.exceptions.ServiceException;
import com.service.api.model.response.ErrorResponse;
import com.service.api.service.MessageCodeService;
import com.service.api.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

/**
 * @author Chakkapong
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //statusCode 100 - 105

    @Autowired
    private MessageCodeService messageCodeService;

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleServiceException(ServiceException exception) throws Exception{
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(DateUtil.formatDateToString(new Date()));
        response.setStatus("fail");
        response.setStatusCode(exception.getMessage());
        response.setMessage(messageCodeService.getMessageByMessageCode(exception.getMessage()));
        return response;
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleDatabaseException(DatabaseException exception) throws Exception {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(DateUtil.formatDateToString(new Date()));
        response.setStatus("fail");
        response.setStatusCode(exception.getMessage());
        response.setMessage(messageCodeService.getMessageByMessageCode(exception.getMessage()));
        return response;
    }
}
