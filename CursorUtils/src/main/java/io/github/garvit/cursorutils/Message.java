package io.github.garvit.cursorutils;

import java.util.Calendar;

public class Message {
    public static final String INBOX = "content://sms/inbox";
    public static final String SENT = "content://sms/sent";

    private String senderAddress;
    private String person;
    private String dateString;
    private String message;
    private Calendar date;
    private String messageType;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDate(Calendar date) {
        this.date = date;
        updateDateString(); 
    }

    public Calendar getDate() {
        return date;
    }

    private void updateDateString() {
        dateString = InternalHelper.calendarToTimeDateString(this.date);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
