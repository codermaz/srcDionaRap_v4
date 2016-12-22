
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//fuer sound dateien
import javax.sound.sampled.*;
import java.io.*;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerBewegung implements ActionListener {
	private DionaRap_Hauptfenster fenster;
	private Move move;

	public ListenerBewegung(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		move = new Move(fenster);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Ausloeser des ActionEvent besorgen
		String befehl = event.getActionCommand();

		switch (befehl) {

		case "links":
			move.richtung(4);
			break;
		case "oben":
			move.richtung(8);
			break;
		case "unten":
			move.richtung(2);
			break;
		case "rechts":
			move.richtung(6);
			break;

		case "links_oben":
			move.richtung(7);
			break;
		case "links_unten":
			move.richtung(1);
			break;
		case "rechts_oben":
			move.richtung(9);
			break;
		case "rechts_unten":
			move.richtung(3);
			break;

		case "shiessen":
			move.schiess();
			break;
		}

		fenster.requestFocus(); // um KeyListener aktiv zu lassen

	}

}
