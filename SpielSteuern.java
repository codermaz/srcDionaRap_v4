import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;

public class SpielSteuern {

	private static int spaltenA = SpielfeldEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielfeldEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielfeldEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielfeldEigenschaften.HINDERNIS_ANZAHL;

	private DionaRap_Hauptfenster fenster;
	private Spielfeld spielFeld;
	DionaRapModel drm;
	private DionaRapController controller;
	ToolBarMenu toolBarMenu;
	private ListenerModel listenerModel;

	private Container spielFlaeche;
	
	
	SpielSteuern(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;

		drm = new DionaRapModel(zeilenA, spaltenA, gegnerA, hindernisA);

		controller = new DionaRapController(drm);

		listenerModel = new ListenerModel(fenster);
		drm.addModelChangedEventListener(listenerModel);
		
		spielFlaeche = fenster.getContentPane();
		// Brett initialisieren
		spielFeld = new Spielfeld(fenster);

		// ToolBar initialisieren
		toolBarMenu= new ToolBarMenu();
		
		spielFlaeche.add(spielFeld.getSpielBrett(), BorderLayout.CENTER);
		spielFlaeche.add(toolBarMenu, BorderLayout.NORTH);
		
		
	}

	public DionaRapModel getDrm() {
		return drm;
	}

	public DionaRapController getController() {
		return controller;
	}

	public Spielfeld getSpielfeld() {
		return spielFeld;
	}


}
