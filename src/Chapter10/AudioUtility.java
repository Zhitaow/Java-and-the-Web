package Chapter10;

import java.applet.*;
import java.net.*;

public class AudioUtility {
	public static AudioClip getAudioClip(URL fileUrl) {//(String filename) {
		if (fileUrl != null) {//(filename != null) {
			//				return Applet.newAudioClip(new URL("http://harp.njit.edu/~zw56/" + filename));
			return Applet.newAudioClip(fileUrl);
		}
		return null;
	}
}