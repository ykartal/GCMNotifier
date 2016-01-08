# GCMNotifier
Java Library for Google Cloud Messaging to send IOS &amp; Android apps.

Use

`HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, message, badge, sound);`
method to send message to a device or devices.
 
**Parameters:**
 - **senderId** GCM sender id, can take from https://developers.google.com/cloud-messaging/
 - **devices** This parameter specifies a list of devices (registration tokens, or IDs) receiving a multicast message. It must contain at least 1 and at most 1000 registration tokens.
 - **title** Indicates notification title. This field is not visible on iOS phones and tablets.
 - **message** Indicates notification body text.
 - **badge** Indicates the badge on client app home icon.
 - **sound** Indicates a sound to play when the device receives the notification. Supports default, or the filename of a sound resource bundled in the app.

**Returns:**
- **HttpResponseMessage** Message list may be contains error messages.

**Throws:**
- **NoDeviceException** - thrown is deviceToken is null
- **NoSenderIdException** - thrown if senderId is null
- **ConnectionException** - thrown if any error is taken from GCM Server except delivery errors

Also have a look at the tests to see alternative usages

There are seperated test cases for android and ios tests but they extends the same class. 
Only replace the senderId and token values in the test classes and run the test.
