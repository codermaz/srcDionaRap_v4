import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlay {

	private File datei;
	private DionaRap_Hauptfenster fenster;
	
	SoundPlay(DionaRap_Hauptfenster _fenster, File _datei) {
		fenster = _fenster;
		datei = _datei;
		playSound();
	}

	private void playSound() {

		if (fenster.isSoundOn()) {

			File sounddatei = datei;

			try {
				// Audiofileformat aus einer sounddatei bestimmen
				AudioFileFormat audioformat = AudioSystem.getAudioFileFormat(sounddatei);
				// Info: System.out.println(audioformat.toString());

				// AudioInputStream erzeugen
				AudioInputStream sound = AudioSystem.getAudioInputStream(sounddatei);

				// Ein Clip laedt einen AudioInputStream in den Speicher (
				// preload )
				Clip audioclip = AudioSystem.getClip();

				// Sounddatei offnen und laden
				audioclip.open(sound);

				// Clip abspielen
				audioclip.start();
			} catch (Exception e) {
				System.out.println("Fehler beim oeffnen der sounddatei :" + datei.toString());
			}
		}
	}

}
