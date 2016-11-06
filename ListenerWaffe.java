

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerWaffe implements ActionListener{
	private SpielBrett brett;
	
	public ListenerWaffe(SpielBrett brett) {
		this.brett= brett;
	}
	
	@Override
	public void actionPerformed (ActionEvent event) {
		String befehl = event.getActionCommand();
		System.out.println(befehl);
		brett.requestFocus(); // um KeyListener aktiv zu lassen
	}
	
}
