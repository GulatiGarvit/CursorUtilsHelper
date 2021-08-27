package io.github.garvit.cursorutils;

import java.util.ArrayList;
import java.util.Calendar;

public class Contact {
    private String name;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> emails;
    private Calendar dateUpdated;
    private String dateUpdatedString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public Calendar getDateCreated() {
        return dateUpdated;
    }

    public void setDateUpdated(Calendar dateUpdated) {
        this.dateUpdated = dateUpdated;
        setDateupdatedString();
    }

    public String getDateUpdatedString() {
        return dateUpdatedString;
    }

    private void setDateupdatedString() {
        dateUpdatedString = InternalHelper.calendarToTimeDateString(this.dateUpdated);
    }
}
