package com.service.api.exceptions;

/**
 * @author Chakkapong
 */
public class DatabaseException extends Exception{

    public DatabaseException() {

    }

    public DatabaseException(String messageCode) {
        super(messageCode);
    }

    public DatabaseException(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String messageCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(messageCode, cause, enableSuppression, writableStackTrace);
    }
}
