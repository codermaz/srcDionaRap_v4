

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBewegung implements ActionListener {
	private SpielBrett brett;

	public ListenerBewegung(SpielBrett brett) {
		this.brett=brett;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) { // Auslöser des ActionEvent besorgen
		String befehl = event.getActionCommand();
		System.out.println(befehl);
		brett.requestFocus(); // um KeyListener aktiv zu lassen

	}

}
