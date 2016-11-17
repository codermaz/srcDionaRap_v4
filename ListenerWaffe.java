 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerWaffe implements ActionListener{
	private DionaRap_Hauptfenster brett;
	
	public ListenerWaffe(DionaRap_Hauptfenster brett) {
		this.brett= brett;
	}
	
	@Override
	public void actionPerformed (ActionEvent event) {
		String befehl = event.getActionCommand();
		System.out.println(befehl);
		brett.requestFocus(); // um KeyListener aktiv zu lassen
	}
	
}
