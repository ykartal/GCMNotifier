package gcm.notifier.model.recieve;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class include result of push message and return messages
 * 
 * @author ykartal
 *
 */
public class HttpResponseMessage {

    private Long multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;

    // return messages as a list
    private List<Message> results = new ArrayList<Message>();

    public Long getMulticast_id() {
	return multicast_id;
    }

    public void setMulticast_id(Long multicast_id) {
	this.multicast_id = multicast_id;
    }

    /**
     * 
     * @return success count
     */
    public int getSuccess() {
	return success;
    }

    public void setSuccess(int success) {
	this.success = success;
    }

    /**
     * 
     * @return failure count
     */
    public int getFailure() {
	return failure;
    }

    public void setFailure(int failure) {
	this.failure = failure;
    }

    public int getCanonical_ids() {
	return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
	this.canonical_ids = canonical_ids;
    }

    public List<Message> getResults() {
	return results;
    }

    public void setResults(List<Message> results) {
	this.results = results;
    }

    /**
     * Return JSON formatted value of this object
     */
    @Override
    public String toString() {
	Gson gson = new GsonBuilder().create();
	String json = gson.toJson(this);
	return json;
    }

}
