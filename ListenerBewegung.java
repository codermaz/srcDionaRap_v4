
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
			move.act(4);
			break;
		case "oben":
			move.act(8);
			break;
		case "unten":
			move.act(2);
			break;
		case "rechts":
			move.act(6);
			break;

		case "links_oben":
			move.act(7);
			break;
		case "links_unten":
			move.act(1);
			break;
		case "rechts_oben":
			move.act(9);
			break;
		case "rechts_unten":
			move.act(3);
			break;

		case "shiessen":
			if (fenster.getDrm().getShootAmount() == 0) // munitionAnzahl
				fenster.getToolBarMenu().startBlinking();
			else
				move.schiess();
			break;

		}

		fenster.requestFocus(); // um KeyListener aktiv zu lassen

	}

}
