import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerMenuLeiste implements ActionListener {

	DionaRap_Hauptfenster fenster;
	
	ListenerMenuLeiste (DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String aktionBefehl = e.getActionCommand();
		if (aktionBefehl.contains("swing.")){
            fenster.setLookAndFeel(aktionBefehl);
            return;
		} else if (aktionBefehl.contains("Unten") || aktionBefehl.contains("Oben")){
            fenster.setToolbarPosition(aktionBefehl);
            fenster.pack();
            return;
		} else if(aktionBefehl.contains("Sound")) {
			if (fenster.isSoundOn()) {
				fenster.getMenuLeiste().getJtbMenuItem().setText("Sound Disabled");
				fenster.setSoundOn(false);
			}
			else {
				fenster.getMenuLeiste().getJtbMenuItem().setText("Sound Enabled");
				fenster.setSoundOn(true);
				
			}
		}
		
	}
	
}
