package gcm.notifier.model.recieve;

public class Message {

    private String message_id;
    private String registration_id;
    private String error;

    public String getMessage_id() {
	return message_id;
    }

    public void setMessage_id(String message_id) {
	this.message_id = message_id;
    }

    public String getRegistration_id() {
	return registration_id;
    }

    public void setRegistration_id(String registration_id) {
	this.registration_id = registration_id;
    }

    public String getError() {
	return error;
    }

    public void setError(String error) {
	this.error = error;
    }

}
