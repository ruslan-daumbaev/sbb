package com.tsystems.sbb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String TIME_PATTERN = "HH:mm";

    public static Date convertTrainTimeToDate(String stringDate){
        return convertStringToDate(stringDate, TIME_PATTERN);
    }

    public static String convertTrainTimeToString(Date date){
        return convertDateTimeToString(date, TIME_PATTERN);
    }

    public static String convertDateTimeToString(Date date, String format){
        if(date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date convertStringToDate(String stringDate, String format){
        if(stringDate == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
