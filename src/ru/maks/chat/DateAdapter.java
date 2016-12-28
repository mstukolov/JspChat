package ru.maks.chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Maxim on 15.12.2016.
 */
public final class DateAdapter {

    private static final ThreadLocal<DateFormat> THREAD_CACHE = new ThreadLocal<DateFormat> ();

    public static DateFormat getFormat() {
        DateFormat format = THREAD_CACHE.get();
        if (format == null) {
            format = new SimpleDateFormat("HH:mm:ss");
            THREAD_CACHE.set(format);
        }
        return format;
    }

}
