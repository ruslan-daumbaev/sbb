package com.tsystems.sbb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String TIME_PATTERN = "HH:mm";

    public static Date convertTrainTimeToDate(String stringDate){
            if(stringDate == null) return null;
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            try {
                return formatter.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
    }

    public static String convertTrainTimeToString(Date date){
        if(date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat(TIME_PATTERN);
        return df.format(date);
    }
}
