

import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

public class ListenerModel implements DionaRapListener{

	
	
	public ListenerModel(SpielBrett spielBrett) {
		//spielBrett.
	}

	
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {
		
		
		
	}

	@Override
	public void statusChanged(GameStatusEvent arg0) {
		
		arg0.isGameWon();
		
	}

}
