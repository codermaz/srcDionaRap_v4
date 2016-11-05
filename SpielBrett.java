

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Dieses Fenster besitzt ein Kindfenster vom Typ JWindow
 *
 */

public class SpielBrett extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int spalten = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilen = SpielBrettEigenschaften.ZEILEN_ANZAHL;

	private Container spielFlaeche = this.getContentPane();
	private JPanel spielBrett = new JPanel();
	private static JLabel[][] spielFelder = new JLabel[spalten][zeilen];

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	public SpielBrett() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("DionaRap");
		this.setResizable(false);

		this.setSize(spalten * SpielBrettEigenschaften.LABEL_DIMENSION, zeilen * SpielBrettEigenschaften.LABEL_DIMENSION);
		this.setLocationRelativeTo(null);

		spielBrett.setLayout(new GridLayout(zeilen, spalten, 0, 0));

		spielFlaeche.setLayout(new BorderLayout());
		spielFlaeche.add(spielBrett);
		this.setVisible(true);

		zeichneErstesBrett();

		NavigationsFenster navisFenster = new NavigationsFenster(this);
		this.addComponentListener(new SpielBrettListener(navisFenster));

	}

	public void leereBrett() {
		for (int i = 0; i < zeilen; i++) // für Zeilen
			for (int j = 0; j < spalten; j++) // für Spalten
				spielFelder[j][i].setText("");
	}
	
	private static Color getInverseFarbe(Color in) {
		return new Color (255-in.getRed(), 255-in.getGreen(), 255-in.getBlue(), in.getAlpha());
	}
	
	public static void setzeFigur (int spalteX, int spalteY, String figur){
		if (figur=="V") 
			spielFelder[spalteX][spalteY].setBackground(Color.red);
		spielFelder[spalteX][spalteY].setForeground(getInverseFarbe(spielFelder[spalteX][spalteY].getBackground()));
		spielFelder[spalteX][spalteY].setText(figur);

	}

	public void zeichneErstesBrett() {
		// Lables für Brett vorbreiten
		int farbeWechsel = 0;
		for (int i = 0; i < zeilen; i++) { // für Zeilen
			for (int j = 0; j < spalten; j++) { // für Spalten
				spielFelder[j][i] = new JLabel("", JLabel.CENTER);
				if (farbeWechsel == 1) {
					spielFelder[j][i].setBackground(SpielBrettEigenschaften.BRETT_COLOR1);
					farbeWechsel = 0;
				} else {
					spielFelder[j][i].setBackground(SpielBrettEigenschaften.BRETT_COLOR2);
					farbeWechsel = 1;
				}
				spielFelder[j][i].setOpaque(true);
				spielBrett.add(spielFelder[j][i]);
			}
			// nochmal die Hintergrundfarbe wechseln, wenn die Spaltenanzahl
			// ungerade ist.
			if ((spalten % 2) == 0) {
				if (farbeWechsel == 0)
					farbeWechsel = 1;
				else
					farbeWechsel = 0;
			}
		}
	}

}
