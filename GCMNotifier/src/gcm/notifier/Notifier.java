package gcm.notifier;

import gcm.notifier.exception.ConnectionException;
import gcm.notifier.exception.NoDeviceException;
import gcm.notifier.exception.NoSenderIdException;
import gcm.notifier.model.recieve.HttpResponseMessage;
import gcm.notifier.model.send.DownstreamHttpMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Notifier {

    private Notifier() {

    }

    private static final String GCM_URL = "https://gcm-http.googleapis.com/gcm/send";

    public static HttpResponseMessage sendGCMMessage(String senderId, String deviceToken, String title, String message, String badge,
	    String sound) throws NoDeviceException, NoSenderIdException, ConnectionException {
	List<String> devices = new ArrayList<String>();
	devices.add(deviceToken);
	return sendGCMMessage(senderId, devices, title, message, badge, sound);
    }

    public static HttpResponseMessage sendGCMMessage(String senderId, List<String> devices, String title, String message, String badge,
	    String sound) throws NoDeviceException, NoSenderIdException, ConnectionException {
	if (senderId == null) {
	    throw new NoSenderIdException();
	}
	if (devices.size() > 0) {
	    try {
		// check parameters and set empty String if anyone is null
		title = title == null ? "" : title;
		message = message == null ? "" : message;
		badge = badge == null ? "1" : badge;
		sound = sound == null ? "" : sound;
		URL url = new URL(GCM_URL);

		DownstreamHttpMessage downstreamHTTPMessage = new DownstreamHttpMessage(devices, title, message);
		downstreamHTTPMessage.setPriority("high"); // To notify IOS apps
							   // when they are not
							   // running
		downstreamHTTPMessage.getNotification().setBadge(badge);
		downstreamHTTPMessage.getNotification().setSound(sound);
		String messageToSend = downstreamHTTPMessage.toString();
		// Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Specify POST method
		conn.setRequestMethod("POST");

		// Set the headers
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key=" + senderId);
		conn.setDoOutput(true);

		// Get connection output stream
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

		byte[] data = messageToSend.getBytes("UTF-8");
		wr.write(data);

		// Send the request and close
		wr.flush();
		wr.close();

		// Get the response
		int responseCode = conn.getResponseCode();
		Gson gson = new GsonBuilder().create();
		if (responseCode == 200) { // ok
		    String response = getResult(conn.getInputStream());
		    HttpResponseMessage httpResponseMessage = gson.fromJson(response.toString(), HttpResponseMessage.class);
		    return httpResponseMessage;
		} else if (responseCode == 400) {
		    throw new ConnectionException("The request could not be parsed as JSON, or it contained invalid fields");
		} else if (responseCode == 401) {
		    throw new ConnectionException(
			    "Authentication Error: 401 There was an error authenticating the sender account, Check the Sender ID");
		}

	    } catch (MalformedURLException e) {
		throw new ConnectionException();
	    } catch (IOException e) {
		throw new ConnectionException();
	    }
	}
	throw new NoDeviceException();
    }

    private static String getResult(InputStream inputStream) throws IOException {
	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();
	return response.toString();
    }

}
