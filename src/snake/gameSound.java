package snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.io.IOException;

public class gameSound {
	private Clip clip;

	public gameSound(String pathname) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(pathname));
			clip = AudioSystem.getClip();
			clip.open(audio);

		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (clip.isRunning())
			clip.stop();
		
		clip.setFramePosition(0);
		clip.start();
	}

	public void stop() {
		clip.stop();
	}
}
