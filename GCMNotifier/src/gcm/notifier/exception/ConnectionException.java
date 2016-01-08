package gcm.notifier.exception;

/**
 * Any problem about GCM server or data content
 * 
 * @author ykartal
 *
 */
public class ConnectionException extends Exception {

    public ConnectionException() {
	super("Could not connected to GCM Server");
    }

    public ConnectionException(String string) {
	super(string);
    }

}
