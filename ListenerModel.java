  

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
			
		
	}

 	@Override
	public void statusChanged(GameStatusEvent arg0) {
 		
		if (arg0.isGameWon()) {
			setDialog("Du hast gewonnen...");
		}
		
		if (arg0.isGameOver()) {
			setDialog("Du hast verloren...");
		}
		
		
	}
 	
 	public void setDialog (String message) {
 		System.out.println(message);
 	}

}
