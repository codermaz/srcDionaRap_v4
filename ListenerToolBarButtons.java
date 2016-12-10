import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListenerToolBarButtons implements ActionListener {
	
DionaRap_Hauptfenster fenster;
	
	ListenerToolBarButtons(DionaRap_Hauptfenster _fenster) {
		fenster=_fenster;
	}

	// TODO ?NeuSpiel-Button funktioniert nicht beim zweiten,dritten ... Mal
	@Override
	public void actionPerformed(ActionEvent event) {
	
		if (event.getActionCommand() == "NeuSpiel") {
			System.out.println("neu spiel");
			fenster.getToolBarMenu().setButtonNeuEnabled(false);
			// Spielfeld saubern
			fenster.getSpielfeld().nullBrett();
			// Neues Spiel anfangen
			fenster.spielStart();
		}
		if (event.getActionCommand() == "Settings") {
			System.out.println("settings");

		}
	}

}
