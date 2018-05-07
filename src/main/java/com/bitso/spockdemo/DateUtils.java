package com.bitso.spockdemo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Some utility methods for date handling.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 3:10 PM
 */
public final class DateUtils {

    public static Date removeHourOfDay(Date date) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(zdt.withHour(0).withMinute(0).withSecond(0).withNano(0).toInstant());
    }

}
