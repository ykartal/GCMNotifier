package gcm.notifier.exception;

public class NoDeviceException extends Exception {

    public NoDeviceException() {
	super("There is no device to send message");
    }

}
