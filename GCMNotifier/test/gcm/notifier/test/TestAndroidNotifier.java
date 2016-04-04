package gcm.notifier.test;

import java.util.ArrayList;

public class TestAndroidNotifier extends TestNotifier {

    @Override
    public void setUp() {
	devices = new ArrayList<String>();
	deviceToken1 = "";
	deviceToken2 = "";
	serverApiKey = "";
	messagePrefix = "GCM Notifier:";
	titlePrefix = "GCM Notifier:";
	soundName = "chime";
	badge = "2";
	if (deviceToken1 != null) {
	    devices.add(deviceToken1);
	}
	if (deviceToken2 != null) {
	    devices.add(deviceToken2);
	}
    }

}
