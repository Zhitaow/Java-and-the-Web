package edu.njit.maze;

import java.applet.*;
import java.net.*;
public class AudioUtility {
	public static AudioClip getAudioClip(String filename) {
		if (filename != null) {
			try {
				return Applet.newAudioClip(new URL("http://harp.njit.edu/~jkj3/content/cs602/exercise10x/audio/" + filename));
			} catch (MalformedURLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}