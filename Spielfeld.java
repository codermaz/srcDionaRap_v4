
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	private static String theme;

	private DionaRap_Hauptfenster fenster;

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	public Spielfeld(DionaRap_Hauptfenster _fenster) {
		theme=SpielBrettEigenschaften.THEME;
		fenster = _fenster;

		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setTitle("DionaRap");
		fenster.setResizable(false);

		fenster.setSize(spaltenA * SpielBrettEigenschaften.LABEL_DIMENSION,
				zeilenA * SpielBrettEigenschaften.LABEL_DIMENSION);
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
				spielFelder[j][i].setIcon(null);
	}

	public void setzeAllePawns() {
		DionaRapModel drm = fenster.getDrm();

		// Brett vorbreiten
		AbstractPawn[] allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "obstacle.gif"); 
			} else if (allePawns[i] instanceof Opponent) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "opponent.gif");
			} else if (allePawns[i] instanceof Player) {
				Player player= (Player) allePawns[i];
				String dir=Integer.toString(player.getViewDirection());
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "player"+dir+".gif");
			} else if (allePawns[i] instanceof Vortex) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "vortex.gif");
			} else if (allePawns[i] instanceof Destruction) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "destruction.gif");
			}
		}
		fenster.requestFocus();
	}

	private static Color getInverseFarbe(Color in) {
		return new Color(255 - in.getRed(), 255 - in.getGreen(), 255 - in.getBlue(), in.getAlpha());
	}

	public static void setzeFigur(int spalteX, int spalteY, String figur) {
		String fs= File.separator;
		ImageIcon image= new ImageIcon(System.getProperty("user.dir") + fs +"images" + fs + theme + fs + figur);
		if (figur == "vortex.gif")
			spielFelder[spalteX][spalteY].setBackground(Color.red);
		if (figur.contains("player"))
System.out.println("dir: " + System.getProperty("user.dir") + "/images/"+theme+"/" + figur);
		spielFelder[spalteX][spalteY].setIcon(image );
		
		//spielFelder[spalteX][spalteY].setBorder(BorderFactory.createEtchedBorder());
		
		//spielFelder[spalteX][spalteY].setForeground(getInverseFarbe(spielFelder[spalteX][spalteY].getBackground()));
		//spielFelder[spalteX][spalteY].setText(figur);

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

			// nochmal die Hintergrundfarbe wechseln, wenn die Spaltenanzahl
			// ungerade ist.
			if ((spaltenA % 2) == 0) {
				if (farbeWechsel == 0)
					farbeWechsel = 1;
				else
					farbeWechsel = 0;
			}
		}
	}

}
