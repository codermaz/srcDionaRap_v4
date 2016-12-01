import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;

public class SpielSteuern {

	private static int spaltenA = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielBrettEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielBrettEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielBrettEigenschaften.HINDERNIS_ANZAHL;

	private DionaRap_Hauptfenster fenster;
	private Spielfeld spielFeld;
	private DionaRapModel drm;
	private DionaRapController controller;
	private ToolBarMenu toolBarMenu;
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
		toolBarMenu= new ToolBarMenu(fenster);
		
		spielFlaeche.add(spielFeld.getSpielBrett(), BorderLayout.CENTER);
		spielFlaeche.add(toolBarMenu, BorderLayout.NORTH);
		
		
		fenster.setVisible(true);
		fenster.pack();
		fenster.requestFocus();
		
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
