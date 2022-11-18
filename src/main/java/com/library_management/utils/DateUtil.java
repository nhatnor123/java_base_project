package com.library_management.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final String strFormat = "dd-MM-yyyy HH:mm:ss.SSS";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(strFormat);

    public static String date2String(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String zoneDate2String(ZonedDateTime date) {
        return date.format(DATE_TIME_FORMATTER);
    }

    public static ZonedDateTime string2Date(String str) {
        return ZonedDateTime.parse(str);
    }

    public static ZonedDateTime now() {
        ZoneId zoneId = ZoneOffset.UTC;
        return ZonedDateTime.now(zoneId);
    }

    public static long nowMillisecond() {
        ZoneId zoneId = ZoneOffset.UTC;
        return ZonedDateTime.now(zoneId).toInstant().toEpochMilli();
    }

    /**
     * Get duration between 2 date
     *
     * @param d1 {@link ZonedDateTime}
     * @param d2 {@link ZonedDateTime}
     * @return d1 - d2
     */
    public static long getDuration(ZonedDateTime d1, ZonedDateTime d2) {
        return d1.toInstant().toEpochMilli() - d2.toInstant().toEpochMilli();
    }

    public static ZonedDateTime millisecondToDate(long mini) {
        Instant instant = Instant.ofEpochMilli(mini);
        return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
