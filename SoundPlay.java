import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlay {

	private File datei;
	Clip audioClip;
	private Boolean soundOn;

	SoundPlay(Boolean _soundOn, File _datei) {
		soundOn = _soundOn;
		datei = _datei;
		initSound();
	}

	private void initSound() {

		File sounddatei = datei;

		try {
			// Audiofileformat aus einer sounddatei bestimmen
			// AudioFileFormat audioformat =
			// AudioSystem.getAudioFileFormat(sounddatei);
			// Info: System.out.println(audioformat.toString());

			// AudioInputStream erzeugen
			AudioInputStream sound = AudioSystem.getAudioInputStream(sounddatei);

			// Ein Clip laedt einen AudioInputStream in den Speicher (
			// preload )
			audioClip = AudioSystem.getClip();

			// Sounddatei offnen und laden
			audioClip.open(sound);

		} catch (Exception e) {
			System.out.println("Fehler beim oeffnen der sounddatei :" + datei.toString());
		}

	}

	public void play() {
		if (audioClip.isRunning())
			audioClip.stop();
		audioClip.setFramePosition(0);
		audioClip.start();
	}

}
