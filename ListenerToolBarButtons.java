import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ListenerToolBarButtons implements ActionListener {

	// TODO ?NeuSpiel-Button funktioniert nicht beim zweiten,dritten ... Mal
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton btn = (JButton) event.getSource();
		DionaRap_Hauptfenster fenster = (DionaRap_Hauptfenster) btn.getTopLevelAncestor();

		if (event.getActionCommand() == "NeuSpiel") {
			System.out.println("neu spiel");
			fenster.getSpielSteuern().toolBarMenu.setButtonNeuEnabled(false);
			// Spielfeld saubern
			fenster.getSpielSteuern().getSpielfeld().nullBrett();
			// Neues Spiel anfangen
			fenster.spielStart();
		}
		if (event.getActionCommand() == "Settings") {
			System.out.println("settings");

		}
	}

}
