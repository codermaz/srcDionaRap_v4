import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;

public class SpielSteuern {

	private static int spaltenA = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielBrettEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielBrettEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielBrettEigenschaften.HINDERNIS_ANZAHL;

	private DionaRap_Hauptfenster fenster;
	private final Spielfeld spielfeld;
	private final DionaRapModel drm;
	private DionaRapController controller;
	ListenerModel listenerModel;

	SpielSteuern(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;

		drm = new DionaRapModel(zeilenA, spaltenA, gegnerA, hindernisA);

		controller = new DionaRapController(drm);
		
		listenerModel = new ListenerModel(fenster);
		drm.addModelChangedEventListener(listenerModel);
		//Brett initialisieren
		spielfeld = new Spielfeld(fenster);
		

	}

	public DionaRapModel getDrm() {
		return drm;
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}


	
	
}
