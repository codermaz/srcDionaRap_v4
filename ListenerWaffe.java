 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerWaffe implements ActionListener{
	private DionaRap_Hauptfenster fenster;
	private Move move;
	
	public ListenerWaffe(DionaRap_Hauptfenster _fenster) {
		this.fenster= _fenster;
		move=new Move(fenster);
	}
	
	@Override
	public void actionPerformed (ActionEvent event) {
		String befehl = event.getActionCommand();
		//System.out.println(befehl);
		move.schiess();
		fenster.requestFocus(); // um KeyListener aktiv zu lassen
	}
	
}
