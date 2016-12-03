  

import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

public class ListenerModel implements DionaRapListener{
	
	private DionaRap_Hauptfenster fenster;
	String punkteStand;
	
	
	public ListenerModel(DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
 	}
	
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {	
		fenster.getSpielSteuern().getSpielfeld().leereBrett();	
		fenster.getSpielSteuern().getSpielfeld().setzeAllePawns();	
		int Punkte=fenster.getSpielSteuern().drm.getScore();
		fenster.getSpielSteuern().toolBarMenu.setPunkteStand(Integer.toString(Punkte));
	}

 	@Override
	public void statusChanged(GameStatusEvent arg0) {
 		
 		DialogWonOver dwo = null;
 		
		if (arg0.isGameWon()) {
			dwo = new DialogWonOver(fenster, 1);
		}
		
		if (arg0.isGameOver()) {
			dwo = new DialogWonOver(fenster, 0);
		}
		
		if (dwo!=null) {
			
			switch(dwo.getAuswahl()) {
			case 0: // Neues Spiel
				fenster.getSpielSteuern().getSpielfeld().nullBrett();
				fenster.spielStart();
				break;
			case 1: // Abbrechen
				System.out.println("Tschüss..." );
				fenster.getSpielSteuern().toolBarMenu.setButtonNeuEnabled(true);
				break;
			}
			
		}
		
	}
 	
 	public void setDialog (String message) {
 		
 		System.out.println(message);
 	}

}
