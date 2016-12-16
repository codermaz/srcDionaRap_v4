import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DialogWonOver {
	private DionaRap_Hauptfenster fenster;
	private String spielerAuswahl[][] = { { "Next Level", "Abbrechen"} , {"Neues Spiel", "Abbrechen" } };
	private int spielerAusgewaehlt; // 0 fur Next Level, 1 fur Abbrechen, 2 fur Neues Spiel
	private int status;
	private String spielStatusMsg[] = { "Game Over", "Sie haben verloren!", "Gewonnen", "Sie haben gewonnen :)",
			"gameover.gif", "gewonnen.gif" };
	private String spielStatus[] = new String[3];

	DialogWonOver(DionaRap_Hauptfenster _fenster, int _status) {
		fenster = _fenster;
		status = _status;
		auswahlNeuSpiel();
	}

	public void auswahlNeuSpiel() {

		String fs = File.separator;
		ImageIcon image;
		
		switch (status) {
		case 0: // verloren
			spielStatus[0] = spielStatusMsg[0];
			spielStatus[1] = spielStatusMsg[1];
			spielStatus[2] = spielStatusMsg[4];
			image = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + spielStatus[2]);
			spielerAusgewaehlt = JOptionPane.showOptionDialog(fenster, spielStatus[1], spielStatus[0],
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
					image, spielerAuswahl[1], spielerAuswahl[1][0]);
			if (spielerAusgewaehlt==0) spielerAusgewaehlt=2;
			break;
		case 1: // gewinnen
			spielStatus[0] = spielStatusMsg[2];
			spielStatus[1] = spielStatusMsg[3];
			spielStatus[2] = spielStatusMsg[5];
			image = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + spielStatus[2]);
			spielerAusgewaehlt = JOptionPane.showOptionDialog(fenster, spielStatus[1], spielStatus[0],
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
					image, spielerAuswahl[0], spielerAuswahl[0][0]);
			
			break;
		}

	}

	public int getAuswahl() {
		return spielerAusgewaehlt;
	}

}
