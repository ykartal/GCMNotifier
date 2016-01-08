package gcm.notifier.exception;

public class ConnectionException extends Exception {

    public ConnectionException() {
	super("Could not connected to GCM Server");
    }

    public ConnectionException(String string) {
	super(string);
    }

}
