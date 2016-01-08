package gcm.notifier.exception;

public class NoSenderIdException extends Exception {

    public NoSenderIdException() {
	super("A valid Sender Id must be send");
    }

}
