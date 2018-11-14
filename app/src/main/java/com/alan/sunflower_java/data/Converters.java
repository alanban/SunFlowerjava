package com.alan.sunflower_java.data;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;

/**
 * @author by bansen
 * date 2018/10/31.
 */
public class Converters {
    @TypeConverter
    public static long calendarToTimestamp(Calendar calendar){
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public static Calendar datestampToCalendar(long value){
        Calendar calendar =Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
