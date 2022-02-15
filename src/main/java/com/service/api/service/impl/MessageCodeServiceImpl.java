package com.service.api.service.impl;

import com.service.api.service.MessageCodeService;
import org.springframework.stereotype.Service;

/**
 * @author Chakkapong
 */
@Service
public class MessageCodeServiceImpl implements MessageCodeService {
    //service code 100-150
    //database code 200-250
    @Override
    public String getMessageByMessageCode(String messageCode) throws Exception {
        String message = null;

        try {
            if (messageCode.equals("200")) {
                message = "Database not found data.";
            } else if (messageCode.equals("201")) {
                message = "Database can not delete null.";
            } else {
                message = "i don't know.";
            }
        } catch (Exception e){
            throw e;
        }
        return message;
    }
}
