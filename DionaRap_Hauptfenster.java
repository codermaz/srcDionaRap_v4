  

import javax.swing.JFrame;

import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.model.objects.*;

public class DionaRap_Hauptfenster extends JFrame {

	SpielSteuern spielSteuern;
	
	
	public DionaRap_Hauptfenster () {
		spielStart();
	}
	
	public void spielStart() {

		
	
		spielSteuern = new SpielSteuern(this);
		
		Navigator navisFenster = new Navigator(this);
		
		this.addComponentListener(new ListenerSpielBrett(navisFenster));

	}
	
	public DionaRapModel getDrm () {
		return spielSteuern.getDrm();
	}

	public static void main(String[] args) {
		new DionaRap_Hauptfenster ();
	}
 
}
