package gcm.notifier.model.send;

import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DownstreamHttpMessage {

    // targets
    private String to;
    private List<String> registration_ids;

    // options
    private String collapse_key;
    private String priority;
    private Boolean content_available = true;
    private Boolean delay_while_idle;
    private int time_to_live = 60 * 60 * 24 * 7 * 4; // 28 gün
    private String restricted_package_name;
    private Boolean dry_run;

    // Payload
    private Properties data = new Properties();
    private Notification notification = new Notification();

    public DownstreamHttpMessage(String to, String title, String body) {
	this.to = to;
	this.getNotification().setTitle(title);
	this.getNotification().setBody(body);
	this.addData("title", title);
	this.addData("message", body);
    }

    public DownstreamHttpMessage(List<String> to, String title, String body) {
	if (to.size() == 1) {
	    this.to = to.get(0);
	} else {
	    this.registration_ids = to;
	}
	this.getNotification().setTitle(title);
	this.getNotification().setBody(body);
	this.addData("title", title);
	this.addData("message", body);
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public List<String> getRegistration_ids() {
	return registration_ids;
    }

    public void setRegistration_ids(List<String> registration_ids) {
	this.registration_ids = registration_ids;
    }

    public String getCollapse_key() {
	return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
	this.collapse_key = collapse_key;
    }

    public String getPriority() {
	return priority;
    }

    public void setPriority(String priority) {
	this.priority = priority;
    }

    public boolean isContent_available() {
	return content_available;
    }

    public void setContent_available(boolean content_available) {
	this.content_available = content_available;
    }

    public boolean isDelay_while_idle() {
	return delay_while_idle;
    }

    public void setDelay_while_idle(boolean delay_while_idle) {
	this.delay_while_idle = delay_while_idle;
    }

    public int getTime_to_live() {
	return time_to_live;
    }

    public void setTime_to_live(int time_to_live) {
	this.time_to_live = time_to_live;
    }

    public String getRestricted_package_name() {
	return restricted_package_name;
    }

    public void setRestricted_package_name(String restricted_package_name) {
	this.restricted_package_name = restricted_package_name;
    }

    public boolean isDry_run() {
	return dry_run;
    }

    public void setDry_run(boolean dry_run) {
	this.dry_run = dry_run;
    }

    public Properties getData() {
	return data;
    }

    public void setData(Properties data) {
	this.data = data;
    }

    public void addData(String key, String value) {
	if (value == null) {
	    return;
	}
	this.data.put(key, value);
    }

    public Notification getNotification() {
	return notification;
    }

    public void setNotification(Notification notification) {
	this.notification = notification;
    }

    @Override
    public String toString() {
	Gson gson = new GsonBuilder().create();
	String json = gson.toJson(this);
	System.out.println(json);
	return json;
    }

}
