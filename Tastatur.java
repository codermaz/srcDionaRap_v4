

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Tastatur {
	private JPanel panel;
	private JButton[] tastatur = new JButton[9];
	private String[] buttonText = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	Tastatur(JPanel panel, Color randColor) {
		this.panel = panel;

		TastaturInsFenster(randColor);
	}

	public void TastaturInsFenster(Color c) {
		// Rand für JWindow mit Panel ermöglichen
		panel.setBorder(BorderFactory.createEtchedBorder(c, c));
		panel.setOpaque(true);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		// Buttons hinzufügen
		setTastatur(this.buttonText);
	}

	// getter und setters
	public void setTastatur(String[] buttonText) {
		panel.removeAll();
		for (int i = 0; i < 9; i++) {
			tastatur[i] = new JButton(buttonText[i]);
			tastatur[i].setPreferredSize(
					new Dimension(SpielBrettEigenschaften.BUTTONS_GROESSE, SpielBrettEigenschaften.BUTTONS_GROESSE));
			panel.add(tastatur[i]);
			// ActionListener für die taste erzeugen und bei der taste
			// registrieren
			switch (i + 1) {
			case 1:
				tastatur[i].setActionCommand("links_oben");
				break;
			case 2:
				tastatur[i].setActionCommand("oben");
				break;
			case 3:
				tastatur[i].setActionCommand("rechts_oben");
				break;
			case 4:
				tastatur[i].setActionCommand("links");
				break;

			case 6:
				tastatur[i].setActionCommand("rechts");
				break;
			case 7:
				tastatur[i].setActionCommand("links_unten");
				break;
			case 8:
				tastatur[i].setActionCommand("unten");
				break;
			case 9:
				tastatur[i].setActionCommand("rechts_unten");
				break;
			}
			if ((i + 1) == 5) {
				tastatur[i].setActionCommand("shoot");
				tastatur[i].addActionListener(new ListenerWaffe());				
			}
			else
				tastatur[i].addActionListener(new ListenerBewegung());
		}
	}

	public JButton[] getTastatur() {
		return tastatur;
	}

}
