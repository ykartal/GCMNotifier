package gcm.notifier.test;

import gcm.notifier.Notifier;
import gcm.notifier.exception.ConnectionException;
import gcm.notifier.exception.NoDeviceException;
import gcm.notifier.exception.NoSenderIdException;
import gcm.notifier.model.recieve.HttpResponseMessage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class TestNotifier {

    protected List<String> devices = new ArrayList<String>();

    protected String deviceToken1;
    protected String deviceToken2;
    protected String senderId;
    protected String messagePrefix;
    protected String titlePrefix;
    protected String soundName;
    protected String badge;

    @Before
    public abstract void setUp();

    @Test
    public void testSendNotifierSuccess() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, message, null, null);
	    Assert.assertTrue("Success Count:" + response.getSuccess(), response.getSuccess() > 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierWrongSenderId() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId + "ERROR", devices, title, message, null, null);
	    Assert.assertEquals("Success Count", response.getSuccess(), 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), e.getMessage().contains("401"));
	}
    }

    @Test
    public void testSendNotifierNoSenderId() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(null, devices, title, message, null, null);
	    Assert.assertTrue(response.getSuccess() == 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), true);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierWithoutAnyToken() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, new ArrayList<String>(), title, message, null, null);
	    Assert.assertFalse("Success Count:" + response.getSuccess(), response.getSuccess() > 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), true);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierWithoutMessage() {
	String title = titlePrefix + "Title Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, null, null, null);
	    // Empty message sent
	    Assert.assertTrue("Success Count:" + response.getSuccess(), response.getSuccess() > 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierWithBadge() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, message, badge, null);
	    Assert.assertTrue("Success Count:" + response.getSuccess(), response.getSuccess() > 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierWithSound() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, message, null, soundName);
	    Assert.assertTrue("Success Count:" + response.getSuccess(), response.getSuccess() > 0);
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierNotRegistered() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	String invalidToken =
		"k2G5SyiWCqY:APA91bHxE4VkBoCudqm2FQyF75U4ucrC6XGlF4A-GWzYwIy0iiRN6hMLSh920Jtds0FIvQe4EAG-SR5MQ4vV9UE9VIYbW_xtJk-LzFU4Qnm1qpoN2Ht00XTASjmcmYj9UsTDPvvUF10N";
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, invalidToken, title, message, null, null);
	    Assert.assertTrue("Failure Count:" + response.getFailure(), response.getFailure() == 1);
	    Assert.assertEquals("NotRegistered", response.getResults().get(0).getError());
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

    @Test
    public void testSendNotifierMessageTooBig() {
	String title = titlePrefix + "Title Success";
	String message = messagePrefix + "Message Success";
	while (message.length() < 5000) {
	    message += message; // increase the message size
	}
	try {
	    HttpResponseMessage response = Notifier.sendGCMMessage(senderId, devices, title, message, null, null);
	    Assert.assertTrue("Failure Count:" + response.getFailure(), response.getFailure() > 1);
	    Assert.assertEquals("MessageTooBig", response.getResults().get(0).getError());
	} catch (NoDeviceException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (NoSenderIdException e) {
	    Assert.assertTrue(e.getMessage(), false);
	} catch (ConnectionException e) {
	    Assert.assertTrue(e.getMessage(), false);
	}
    }

}
