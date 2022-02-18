package com.service.api.constant;

/**
 * @author Chakkapong
 */
public class ExceptionConstant {

    private ExceptionConstant() {
        throw new IllegalStateException();
    }

    public static final String DATABASE_NOT_FOUND = "200";
    public static final String DATABASE_CANNOT_DELETE = "201";
    public static final String REQUIRED = "300";
    public static final String TOKEN_NOT_FOUND = "998";
    public static final String TOKEN_EXPIRED = "999";
}
