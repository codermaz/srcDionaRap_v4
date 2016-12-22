  
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

public class ListenerModel implements DionaRapListener{
	
	private DionaRap_Hauptfenster fenster;
	private int gegnerAnfang;
	private int gegnerAktuell;
	private int gegnerProzent;
	private int munitionAnzahl;
	private int Punkte;
	private DionaRapModel drm;
	
	
	public ListenerModel(DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
		gegnerAnfang= Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.gegnerA));
 	}
	
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {	
		fenster.getSpielfeld().leereBrett();	
		fenster.getSpielfeld().setzeAllePawns();	
		drm = fenster.getDrm(); 
		ToolBarMenu tBar = fenster.getToolBarMenu();
		
		// JToolBar tBar = fenster.getSpielSteuern().getToolBarMenu().getToolBar(); nur für Info
		// ((ToolBarMenu) tBar).setPunkteStand(Integer.toString(Punkte));
		//PunkteStand bei ToolBarMenu aktualisieren
		Punkte = drm.getScore() + (fenster.currentPunkte);
		tBar.setPunkteStand(Integer.toString(Punkte));		
		
		//ProgressStand bei ToolBarMenu aktualisieren
		gegnerAktuell= drm.getOpponentCount();
		gegnerProzent=(int) (100-((double)gegnerAktuell / (double)gegnerAnfang)*100);
		tBar.setSpielfortschritWert(gegnerProzent);
		
		// Munition Anzahl aktualisieren
		munitionAnzahl= drm.getShootAmount();
		tBar.showMunitionAnzahl(munitionAnzahl);
	}

 	@Override
	public void statusChanged(GameStatusEvent arg0) {
 		
 		DialogWonOver dwo = null;
 		
		if (arg0.isGameWon()) 
			dwo = new DialogWonOver(fenster, 1);
	
		if (arg0.isGameOver()) 
			dwo = new DialogWonOver(fenster, 0);
		
		if (dwo.getAuswahl()==0) {  // Next Level
			if (fenster.currentLevel<fenster.LEVEL_MAX) fenster.currentLevel++;
			DionaRap_Hauptfenster.currentPunkte += drm.getScore(); 
			fenster.getSpielfeld().stopBlinking(); // Bonus (Hertz) loeschen
			fenster.spielStart();
		}
		else if (dwo.getAuswahl()==1)  // Abbrechen
			fenster.getToolBarMenu().setButtonNeuEnabled(true);
		else if (dwo.getAuswahl()==2){ // Neues Spiel
			fenster.currentLevel=0;
			DionaRap_Hauptfenster.currentPunkte = 0; 
			fenster.getSpielfeld().stopBlinking(); // Bonus (Hertz) loeschen
			fenster.spielStart();
		}
	}
}
