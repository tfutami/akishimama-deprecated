package com.akishimama.web.utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Created by sat8bit on 2017/01/02.
 */
public class DateTimeUtils {
    public static ZonedDateTime now() {
        return ZonedDateTime.now().withNano(0);
    }

    public static ZonedDateTime convert(Date date) {
        return ZonedDateTime.from(date.toInstant().atZone(getZoneId()));
    }

    public static Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    private static ZoneId getZoneId() {
        return ZoneId.of("Asia/Tokyo");
    }

    public static class DateTimeJapaneseFormatter {
        public static String format(ZonedDateTime zonedDateTime) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
            return dateTimeFormatterBuilder.appendValue(ChronoField.YEAR)
                    .appendLiteral("/")
                    .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                    .appendLiteral("/")
                    .appendValue(ChronoField.DAY_OF_MONTH, 2)
                    .appendLiteral(" ")
                    .appendValue(ChronoField.HOUR_OF_DAY, 2)
                    .appendLiteral(":")
                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                    .appendLiteral(":")
                    .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
                    .toFormatter().format(zonedDateTime)
                    ;
        }

        public static String format(Date date) {
            return format(DateTimeUtils.convert(date));
        }
    }
}
