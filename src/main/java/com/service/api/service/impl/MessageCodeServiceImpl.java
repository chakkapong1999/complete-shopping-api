package com.service.api.service.impl;

import com.service.api.constant.ExceptionConstant;
import com.service.api.service.MessageCodeService;
import org.springframework.stereotype.Service;

/**
 * @author Chakkapong
 */
@Service
public class MessageCodeServiceImpl implements MessageCodeService {
    //service code 100-150
    //database code 200-250
    //validate code 300-350
    @Override
    public String getMessageByMessageCode(String messageCode) throws Exception {
        String message = null;

        try {
            if (messageCode.equals(ExceptionConstant.DATABASE_NOT_FOUND)) {
                message = "Database not found data.";
            } else if (messageCode.equals(ExceptionConstant.DATABASE_CANNOT_DELETE)) {
                message = "Database can not delete null.";
            } else if (messageCode.equals(ExceptionConstant.REQUIRED)) {
                message = "Required field(s) is/are missing.";
            } else {
                message = "i don't know.";
            }
        } catch (Exception e){
            throw e;
        }
        return message;
    }
}
