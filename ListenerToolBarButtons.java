import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListenerToolBarButtons implements ActionListener {
	
DionaRap_Hauptfenster fenster;
	
	ListenerToolBarButtons(DionaRap_Hauptfenster _fenster) {
		fenster=_fenster;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "NeuSpiel") {
			fenster.getToolBarMenu().setButtonNeuEnabled(false);
			// Neues Spiel anfangen
			fenster.currentLevel=0;
			DionaRap_Hauptfenster.currentPunkte = 0; 
			fenster.spielStart();
		}
		if (event.getActionCommand() == "Settings") {
			fenster.getSpielfeld().stopBlinking(); // fuer Hertz
			new DialogSettings(fenster);
		}
	}

}
