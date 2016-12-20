
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.model.objects.Destruction;
import de.fhwgt.dionarap.model.objects.Obstacle;
import de.fhwgt.dionarap.model.objects.Opponent;
import de.fhwgt.dionarap.model.objects.Player;
import de.fhwgt.dionarap.model.objects.Vortex;

/**
 * 
 * Dieses Fenster besitzt ein Kindfenster vom Typ JWindow
 *
 */

public class Spielfeld extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int spaltenA;
	private static int zeilenA;

	private static JLabel[][] labelFelder;
	private static String theme;

	private DionaRap_Hauptfenster fenster;
	private ListenerMouse listenerMouse;

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	 Spielfeld(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		zeilenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.zeilenA));
		spaltenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA)); 
		labelFelder = new JLabel[spaltenA][zeilenA];
		
		fenster.setPreferredSize(new Dimension(spaltenA * Settings.LABEL_DIMENSION,
				zeilenA * Settings.LABEL_DIMENSION));
		fenster.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(zeilenA, spaltenA, 0, 0));

		listenerMouse = new ListenerMouse(fenster);
		initSpielFeld();
		listenerMouse.setLabelFelder(labelFelder);
	}

	public JPanel getSpielFeld() {
		return this;
	}

	public void setTheme(String _theme){
		theme=_theme;
	}
	

	public void nullBrett() {
		this.removeAll();
	}

	public void leereBrett() {
		for (int i = 0; i < zeilenA; i++) // fuer Zeilen
			for (int j = 0; j < spaltenA; j++) // fuer Spalten
				labelFelder[j][i].setIcon(null);
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
				Player player = (Player) allePawns[i];
				String dir = Integer.toString(player.getViewDirection());
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "player" + dir + ".gif");
			} else if (allePawns[i] instanceof Vortex) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "vortex.gif");
			} else if (allePawns[i] instanceof Destruction) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "destruction.gif");
			}  else if (allePawns[i] instanceof Ammo) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "ammo.png");
			}
		}
	}

	private static Color getInverseFarbe(Color in) {
		return new Color(255 - in.getRed(), 255 - in.getGreen(), 255 - in.getBlue(), in.getAlpha());
	}

	public void setzeFigur(int spalteX, int spalteY, String figur) {
		String fs = File.separator;
		theme = Settings.THEME;
		ImageIcon image = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + theme + fs + figur);
		if (figur == "vortex.gif")
			labelFelder[spalteX][spalteY].setBackground(Color.red);

		labelFelder[spalteX][spalteY].setIcon(image);
	
		// spielFelder[spalteX][spalteY].setBorder(BorderFactory.createEtchedBorder());
		// spielFelder[spalteX][spalteY].setForeground(getInverseFarbe(spielFelder[spalteX][spalteY].getBackground()));
		// spielFelder[spalteX][spalteY].setText(figur);
	}

	public void initSpielFeld() {
		// Labels fuer Brett vorbereiten
		int farbeWechsel = 0;
		for (int i = 0; i < zeilenA; i++) { // fuer Zeilen
			for (int j = 0; j < spaltenA; j++) { // fuer Spalten
				labelFelder[j][i] = new JLabel("", JLabel.CENTER);
				if (farbeWechsel == 1) {
					labelFelder[j][i].setBackground(Settings.BRETT_COLOR1);
					farbeWechsel = 0;
				} else {
					labelFelder[j][i].setBackground(Settings.BRETT_COLOR2);
					farbeWechsel = 1;
				}
				labelFelder[j][i].setOpaque(true);
				labelFelder[j][i].addMouseListener(listenerMouse);

				this.add(labelFelder[j][i]);
				
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
