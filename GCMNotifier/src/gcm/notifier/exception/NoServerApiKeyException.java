package gcm.notifier.exception;

/**
 * If no sender id passed to the method
 * 
 * @author ykartal
 *
 */
public class NoServerApiKeyException extends Exception {

    public NoServerApiKeyException() {
	super("A valid Sender Id must be send");
    }

}
