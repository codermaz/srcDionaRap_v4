

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBewegung implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) { // Auslöser des ActionEvent besorgen
		String befehl = event.getActionCommand();
		System.out.println(befehl);

	}

}
