package com.service.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chakkapong
 */
public class DateUtil {
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String formatDateToString(Date date) {
        SimpleDateFormat format;
        format = new SimpleDateFormat(DATE_TIME);
        return format.format(date);
    }
}
