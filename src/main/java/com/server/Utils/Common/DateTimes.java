package com.server.Utils.Common;

import lombok.NonNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimes {
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final static ZoneOffset zoneOffset = ZoneOffset.of("+8");
    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        return LocalDateTime.now().format(formatter);
    }

    public static String timestamp(Timestamp time) {
        if (time == null) {
            return null;
        }
        return time.toLocalDateTime().format(formatter);
    }

    public static String timestamp(Timestamp time, String format) {
        if (time == null) {
            return null;
        }
        return time.toLocalDateTime().format(DateTimeFormatter.ofPattern(format));
    }


    public static String timestamp(long time) {
        if (time <= 0) {
            return null;
        }
        return timestamp(new Timestamp(time));
    }

    public static String timestamp(long time, String format) {
        if (time <= 0) {
            return null;
        }
        return timestamp(new Timestamp(time), format);
    }

    /**
     * 将时间转换成毫秒
     *
     * @param time
     * @return
     */
    public static long parse(@NonNull final String time) {
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        if (dateTime == null) {
            throw new IllegalArgumentException("dateTime parse error:" + time);
        }
        return dateTime.toInstant(zoneOffset).toEpochMilli();
    }

    public static String formatDate(Date date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseDate(String strDate, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(strDate);
    }

    public static Date timestampToDate(Timestamp timeStamp) throws ParseException {
        return timestampToDate(timeStamp, DEFAULT_FORMAT);
    }

    public static Date timestampToDate(Timestamp timeStamp, String format) throws ParseException {
        String parseString = timestamp(timeStamp, format);
        return parseDate(parseString, format);
    }

    /**
     * 获取当前的剩余时间
     *
     * @param currentDate
     * @return
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }
}
