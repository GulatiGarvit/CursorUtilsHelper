# Cursor Utils

CursorUtils is an Android library written in JAVA for dealing with basic android content provider tasks in an easier way, without having to manage cursors yourself.
### Supported
- SMS
- Contacts

### To be supported soon
- Call Logs
- Blocked Numbers
- Calendar events

## Installation

Add the following in your project level `build.gradle` at the end of repositories:
```bash
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the following dependency in your app level `build.gradle`
```bash
implementation 'com.github.GulatiGarvit:CursorUtils:1.1.0'
  ```

## Usage

### Reading messages

```JAVA

import io.github.garvit.basicutilityhelper.Message;

private void fetchSms() {
//CursorUtils.with(context).getSMS(Uri, count)
//The method returns an array (Message[])
Message[] messages = CursorUtils.with(this)
		.getSMS(CursorUtils.INBOX, 20);
}
```
| method             | return type        | usage                                                                                                                   |
|--------------------|--------------------|-------------------------------------------------------------------------------------------------------------------------|
| `getSenderAddress()` | java.lang.String   | Returns the sender address for the message (It is the user's phone number for outgoing messages)                        |
| `getDate()`          | java.util.Calendar | Returns a calendar object for the message's Sent/Received date/time                                                          |
| `getDateString()`    | java.util.String   | Returns a stringified, readable version of the date/time (hh:mm DD:MM:YYYY)                                                  |
| `getMessage()`       | java.util.String   | Returns the message body                                                                                                |
| `getMessageType()`   | java.util.String   | Returns the URI passed initially, while creating the request (Either of CursorUtils.INBOX or CursorUtils.SENT |

<br>

### Reading Contacts

```JAVA

import io.github.garvit.basicUtilityHelper.Contact;

private void fetchContacts() {
//CursorUtils.with(context).getContacts()
//The method returns an array (Contact[])
Contact[] contacts = CursorUtils.with(this)
				.getContacts();
}

```
| Method                  | Return type         | Usage                                                                      |
|-------------------------|---------------------|----------------------------------------------------------------------------|
| `getName()`               | java.lang.String    | Returns the contact name                                                   |
| `getPhoneNumbers()`       | java.util.ArrayList | Returns an ArrayList<String> with all the phone numbers under this contact |
| `getEmails()`             | java.util.ArrayList | Returns an ArrayList<String> with all the emails under this contact        |
| `getDateUpdated()`        | java.util.Calendar  | Returns the date the contact was last updated                              |
| `getDateUpdaatedString()` | java.util.String    | Returns a stringified, readable version of the date (hh:mm DD:MM:YYYY)     |

<br>

### Reading Call Logs

```JAVA

import io.github.garvit.basicUtilityHelper.Contact;

private void fetchCallLogs() {
//CursorUtils.with(context).getCallLogs(count)
//The method returns an array (CallLog[])
CallLog[] callLogs = CursorUtils.with(this)
				.getCallLogs(20);
}

```
| Method                  | Return type         | Usage                                                                      |
|-------------------------|---------------------|----------------------------------------------------------------------------|
| `getPhoneNumber()`       | java.util.String | Returns the phone number present in the call log |
| `getDate()`          | java.util.Calendar | Returns a calendar object for the call's date/ti                               |
| `getDateString()`    | java.util.String   | Returns a stringified, readable version of the date/time (hh:mm DD:MM:YYYY)    |
| `getDuration()`       | int | Returns the call duration (in seconds)                                                       |
| `getCallType()`       | int | Returns the type of call (CallLog.INCOMING_TYPE or CallLog.OUTGOING_TYPE or CallLog.MISSED_TYPE                      |


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
This project is licensed under the terms of the [MIT license](https://choosealicense.com/licenses/mit/)

```
MIT License

Copyright (c) 2021 Garvit Gulati

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
