

import javax.swing.JFrame;

import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.model.objects.*;

public class DionaRap_Hauptfenster extends JFrame {
	private static int spaltenA = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielBrettEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielBrettEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielBrettEigenschaften.HINDERNIS_ANZAHL;

	public DionaRap_Hauptfenster () {
		spielStart();
	}
	
	public void spielStart() {
		//Brett initialisieren
		Spielfeld spielfeld = new Spielfeld(this);
		
		ListenerModel listenerModel = new ListenerModel(spielfeld);
		
		
		Navigator navisFenster = new Navigator(this);
		
		this.addComponentListener(new ListenerSpielBrett(navisFenster));
		
		DionaRapModel drm = new DionaRapModel(zeilenA, spaltenA, gegnerA, hindernisA);
		// Brett vorbreiten 
		AbstractPawn[] allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				Spielfeld.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "H");
			} else if (allePawns[i] instanceof Opponent) {
				Spielfeld.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "G");
			} else if (allePawns[i] instanceof Player) {
				Spielfeld.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "S");
			} else if (allePawns[i] instanceof Vortex) {
				Spielfeld.setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "V");
			}
		}
		
		
		

	}

	public static void main(String[] args) {
		new DionaRap_Hauptfenster ();
	}
 
}
