package gcm.notifier.exception;

/**
 * If no sender id passed to the method
 * 
 * @author ykartal
 *
 */
public class NoSenderIdException extends Exception {

    public NoSenderIdException() {
	super("A valid Sender Id must be send");
    }

}
