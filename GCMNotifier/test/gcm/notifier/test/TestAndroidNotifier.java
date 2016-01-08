package gcm.notifier.test;

import java.util.ArrayList;

public class TestAndroidNotifier extends TestNotifier {

    @Override
    public void setUp() {
	devices = new ArrayList<String>();
	deviceToken1 =
		"APA91bGsJd-eunu2yt_gh7vhFvGoA8nCViNrxtW9oyc4lYZG3CeO6ANmusRS3B9OG-ntYSOUqQfWrASPB-FaOnm25wt8YoAO_5eUJt_bpVUzagIVBoEriUtx9D_FTheP_GkoBrvWWFzJ";
	deviceToken2 =
		"APA91bGsJd-eunu2yt_gh7vhFvGoA8nCViNrxtW9oyc4lYZG3CeO6ANmusRS3B9OG-ntYSOUqQfWrASPB-FaOnm25wt8YoAO_5eUJt_bpVUzagIVBoEriUtx9D_FTheP_GkoBrvWWFzJ";
	senderId = "";// seNDER ID for android
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
