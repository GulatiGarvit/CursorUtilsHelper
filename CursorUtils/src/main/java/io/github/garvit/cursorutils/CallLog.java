package io.github.garvit.cursorutils;

import java.util.Calendar;

public class CallLog {
    public static final int OUTGOING_TYPE = android.provider.CallLog.Calls.OUTGOING_TYPE;
    public static final int INCOMING_TYPE = android.provider.CallLog.Calls.INCOMING_TYPE;
    public static final int MISSED_TYPE = android.provider.CallLog.Calls.MISSED_TYPE;

    private String phoneNumber;
    private int callType;
    private String dateString;
    private Calendar date;
    private int duration;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
        updateDateString();
    }

    private void updateDateString() {
        dateString = InternalHelper.calendarToTimeDateString(this.date);
    }

    public String getDateString() {
        return dateString;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
