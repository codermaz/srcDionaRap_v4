

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerWaffe implements ActionListener{
	
	@Override
	public void actionPerformed (ActionEvent event) {
		String befehl = event.getActionCommand();
		System.out.println(befehl+"***");

	}
	
}
