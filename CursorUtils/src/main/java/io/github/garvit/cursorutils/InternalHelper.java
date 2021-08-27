package io.github.garvit.cursorutils;

import java.util.Calendar;

public class InternalHelper {
    public static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static String zerosForTimeString(int i) {
        if(i < 10)
            return "0"+String.valueOf(i);
        return String.valueOf(i);
    }

    public static String calendarToTimeDateString(Calendar calendar) {
        return InternalHelper.calendarToTimeString(calendar, true, false) + " " + InternalHelper.calendarToDateString(calendar, false, false);
    }

    public static String calendarToTimeString(Calendar calendar, boolean is24hourFormat, boolean includeSeconds) {
        String time = zerosForTimeString(calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                zerosForTimeString(calendar.get(Calendar.MINUTE));
        if(includeSeconds)
            time += ":" + zerosForTimeString(calendar.get(Calendar.SECOND));

        return time;
    }

    public static String calendarToDateString(Calendar calendar, boolean isMonthInWords, boolean includeDay) {
        String date = zerosForTimeString(calendar.get(Calendar.DAY_OF_MONTH));
        if(isMonthInWords)
           date +=  months[calendar.get(Calendar.MONTH)] + " ";
        else
            date += "/" + zerosForTimeString(calendar.get(Calendar.MONTH)+1) + "/";
        date += calendar.get(Calendar.YEAR);
        if(includeDay)
            date = days[calendar.get(Calendar.DAY_OF_WEEK)] + " " + date;

        return date;
    }

    public static Calendar getCalendar(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar;
    }
}
