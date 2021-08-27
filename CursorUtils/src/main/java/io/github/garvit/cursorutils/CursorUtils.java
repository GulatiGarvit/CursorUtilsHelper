package io.github.garvit.cursorutils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import java.util.ArrayList;

import static io.github.garvit.cursorutils.InternalHelper.getCalendar;

public class CursorUtils {
    private final Context context;

    private CursorUtils(Context context) {
        this.context = context;
    }

    public static CursorUtils with(Context context) {
        return new CursorUtils(context);
    }

    public Message[] getSMS(String uri, int count) {
        Message[] finalMessages;
        ArrayList<Message> messages = new ArrayList<>();
        int i = 1;

        Cursor cursor = context.getContentResolver().query(Uri.parse(uri), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                Message message = new Message();
                message.setSenderAddress(cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS)));
                message.setPerson(cursor.getString(cursor.getColumnIndex(Telephony.Sms.PERSON)));
                message.setDate(getCalendar(Long.parseLong(cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE)))));
                message.setMessage(cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY)));
                message.setMessageType(uri);
                messages.add(message);
            } while (cursor.moveToNext() && i++ < count);

        }
        finalMessages = messages.toArray(new Message[messages.size()]);
        cursor.close();

        return finalMessages;
    }

    public Contact[] getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact[] finalContacts;

        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contact.setName(name);
                long date = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP));
                contact.setDateUpdated(getCalendar(date));
                ArrayList<String> phoneNumbers = new ArrayList<>();
                ArrayList<String> emails = new ArrayList<>();

                Cursor emailsCur = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)), null, null);
                while (emailsCur.moveToNext()) {
                    emails.add(emailsCur.getString(emailsCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                    break;
                }
                emailsCur.close();
                contact.setEmails(emails);

                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))}, null);
                    pCur.moveToFirst();
                    do {
                        String temp = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        boolean isNew = true;
                        if (phoneNumbers.size() == 0)
                            phoneNumbers.add(temp);
                        else {
                            for (int i = 0; i < phoneNumbers.size(); i++) {
                                if (phoneNumbers.get(i).trim().replaceAll(" ", "").equals(temp))
                                    isNew = false;
                            }
                            if (isNew)
                                phoneNumbers.add(temp);
                        }
                    } while (pCur.moveToNext());
                    pCur.close();
                }

                contact.setPhoneNumbers(phoneNumbers);
                contacts.add(contact);
            } while (cursor.moveToNext());

            cursor.close();
        }

        finalContacts = contacts.toArray(new Contact[contacts.size()]);

        return finalContacts;
    }

    public CallLog[] getCallLogs(int count) {
        ArrayList<CallLog> callLogs = new ArrayList<>();
        CallLog[] finalCallLogs;

        Cursor cursor = context.getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            CallLog callLog = new CallLog();

            String phoneNumber = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            int callType = Integer.parseInt(cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE)));
            long date = Long.parseLong(cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.DATE)));
            int callDuration = Integer.parseInt(cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.DURATION)));

            callLog.setPhoneNumber(phoneNumber);
            callLog.setCallType(callType);
            callLog.setDate(getCalendar(date));
            callLog.setDuration(callDuration);

            callLogs.add(callLog);
        }
        cursor.close();

        finalCallLogs = callLogs.toArray(new CallLog[callLogs.size()]);

        return finalCallLogs;
    }
}
