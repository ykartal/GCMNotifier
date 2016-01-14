package gcm.notifier.test;

import java.util.ArrayList;

public class TestIOSNotifier extends TestNotifier {

    @Override
    public void setUp() {
	devices = new ArrayList<String>();
	deviceToken1 =
		"lEUmh70qIxQ:APA91bG_Ee1Q1bIeLwktXQOgXvo2pKm8ouQvTfZn6--EfjBqu9STzdK8HWERdq-ywCify3AxiA3q9LoclXq93_lRtCOl0NqBVr-uPS9ju6Vs1z2hS09K0B6UdQwEcmi09GslAyP78pff";
	deviceToken2 =
		"lEUmh70qIxQ:APA91bG_Ee1Q1bIeLwktXQOgXvo2pKm8ouQvTfZn6--EfjBqu9STzdK8HWERdq-ywCify3AxiA3q9LoclXq93_lRtCOl0NqBVr-uPS9ju6Vs1z2hS09K0B6UdQwEcmi09GslAyP78pff";
	serverApiKey = "AIzaSyDzOezvtoLVeFgDOd4lIDiOEZJeYO6xJcI";
	messagePrefix = "GCM Notifier:";
	titlePrefix = "GCM Notifier:";
	soundName = "default";
	badge = "2";
	if (deviceToken1 != null) {
	    devices.add(deviceToken1);
	}
	if (deviceToken2 != null) {
	    devices.add(deviceToken2);
	}
    }

}
