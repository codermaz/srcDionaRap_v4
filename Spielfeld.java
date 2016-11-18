 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.*;

/**
 * 
 * Dieses Fenster besitzt ein Kindfenster vom Typ JWindow
 *
 */

public class Spielfeld extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int spaltenA = SpielBrettEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielBrettEigenschaften.ZEILEN_ANZAHL;
	

	private Container spielFlaeche;
	private JPanel spielBrett = new JPanel();
	private static JLabel[][] spielFelder = new JLabel[spaltenA][zeilenA];

	private DionaRap_Hauptfenster fenster;

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	public Spielfeld(DionaRap_Hauptfenster _fenster) {
		fenster= _fenster; 
		
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setTitle("DionaRap");
		fenster.setResizable(false);

		fenster.setSize(spaltenA * SpielBrettEigenschaften.LABEL_DIMENSION, zeilenA * SpielBrettEigenschaften.LABEL_DIMENSION);
		fenster.setLocationRelativeTo(null);

		spielBrett.setLayout(new GridLayout(zeilenA, spaltenA, 0, 0));
		spielFlaeche = fenster.getContentPane();
		spielFlaeche.setLayout(new BorderLayout());
		spielFlaeche.add(spielBrett);
		fenster.setVisible(true);

		zeichneErstesBrett();

		
		ListenerKeyPressed keyPressed = new ListenerKeyPressed(fenster);
		fenster.addKeyListener(keyPressed);

		
		

	}

	public void leereBrett() {
		for (int i = 0; i < zeilenA; i++) // für Zeilen
			for (int j = 0; j < spaltenA; j++) // für Spalten
				spielFelder[j][i].setText("");
	}
	
	public void setzeAllePawns() {
	   DionaRapModel drm = fenster.getDrm();
		
		// Brett vorbreiten 
		AbstractPawn[] allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "H");
			} else if (allePawns[i] instanceof Opponent) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "G");
			} else if (allePawns[i] instanceof Player) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "S");
			} else if (allePawns[i] instanceof Vortex) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "V");
			} else if (allePawns[i] instanceof Destruction) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "*");
			}
		}
		fenster.requestFocus();
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
		for (int i = 0; i < zeilenA; i++) { // für Zeilen
			for (int j = 0; j < spaltenA; j++) { // für Spalten
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
			
			// nochmal die Hintergrundfarbe wechseln, wenn die Spaltenanzahl ungerade ist.
			if ((spaltenA % 2) == 0) {
				if (farbeWechsel == 0)
					farbeWechsel = 1;
				else
					farbeWechsel = 0;
			}
		}
	}

}
