
import javax.swing.JTextField;

import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.model.objects.*;

public class DionaRap_Hauptfenster {
	private static int spaltenA = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielBrettEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielBrettEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielBrettEigenschaften.HINDERNIS_ANZAHL;

	public static void spielStart() {
		//Brett initialisieren
		new SpielBrett();
		DionaRapModel drm = new DionaRapModel(zeilenA, spaltenA, gegnerA, hindernisA);
		AbstractPawn[] allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				SpielBrett.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "H");
			} else if (allePawns[i] instanceof Opponent) {
				SpielBrett.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "G");
			} else if (allePawns[i] instanceof Player) {
				SpielBrett.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "S");
			} else if (allePawns[i] instanceof Vortex) {
				SpielBrett.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "V");
			}

		}
		
	
		

	}

	public static void main(String[] args) {
		spielStart();
	}

}
