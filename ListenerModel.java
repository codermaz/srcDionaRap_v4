  

import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

public class ListenerModel implements DionaRapListener{
	
	private DionaRap_Hauptfenster fenster;
	
	
	public ListenerModel(DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
 	}

	
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {
		
		fenster.getSpielSteuern().getSpielfeld().leereBrett();
		
		fenster.getSpielSteuern().getSpielfeld().setzeAllePawns();		
	}

 	@Override
	public void statusChanged(GameStatusEvent arg0) {
 		
 		DialogWonOver dwo = null;
 		
		if (arg0.isGameWon()) {
			setDialog("Du hast gewonnen...");
			dwo = new DialogWonOver(fenster, 1);
		}
		
		if (arg0.isGameOver()) {
			setDialog("Du hast verloren...");
			dwo = new DialogWonOver(fenster, 0);
		}
		
		if (dwo!=null) {
			
			switch(dwo.getAuswahl()) {
			case 0: // Neues Spiel
				System.out.println("neue " );
				fenster.getSpielSteuern().getSpielfeld().nullBrett();
				fenster.spielStart();
				break;
			case 1: // Abbrechen
				System.out.println("Tschüss..." );
				dwo=null;
				break;
			}
			
		}
		
		
		
		//fenster.getSpielSteuern().getSpielfeld().leereBrett();
		//fenster.spielStart();
	}
 	
 	public void setDialog (String message) {
 		
 		System.out.println(message);
 	}

}
