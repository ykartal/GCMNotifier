package gcm.notifier.exception;

/**
 * There is no device token to send messages
 * 
 * @author ykartal
 *
 */
public class NoDeviceException extends Exception {

    public NoDeviceException() {
	super("There is no device to send message");
    }

}
