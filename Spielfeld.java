
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Random;

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
	private JLabel[][] leerFelder;
	private static String theme;

	private DionaRap_Hauptfenster fenster;
	private ListenerMouse listenerMouse;
	private DionaRapModel drm;
	private AbstractPawn[] allePawns;

	private int ammoMalValue;

	private Bonus bonus;
	private boolean hertzAddiert;

	private Thread threadBonus;
	private RunnableHertzAddition rha;

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	Spielfeld(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		zeilenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.zeilenA));
		spaltenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA));
		labelFelder = new JLabel[spaltenA][zeilenA];
		leerFelder = new JLabel[spaltenA][zeilenA];
		hertzAddiert = false;

		fenster.setPreferredSize(
				new Dimension(spaltenA * Settings.LABEL_DIMENSION, zeilenA * Settings.LABEL_DIMENSION));
		fenster.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(zeilenA, spaltenA, 0, 0));

		drm = fenster.getDrm();
		addAmmos();
		listenerMouse = new ListenerMouse(fenster);
		initSpielFeld();
		listenerMouse.setLabelFelder(labelFelder);

		// runnable Instanz
		rha = new RunnableHertzAddition(this);
		// Thread erzeugen mit runnable Instanz
		threadBonus = new Thread(rha);
	}

	private void addAmmos() {

		// ShootAmount : Legt fest, wieviele Schuesse der Spieler zu Beginn hat.
		// -1 steht fuer eine unbegrenzte Anzahl an Munition.
		fenster.getDrm().setShootAmount(Settings.MUNITION_ANZAHL_ZUBEGIN);
		// AmmoValue : Setzt die Anzahl der Munition fuer ein Ammo-Objekt
		ammoMalValue = Settings.MUNITION_ANZAHL_FUREINAMMO + (int) Math.floor(fenster.getCurrentLevel() / 3);
		fenster.getDrm().setAmmoValue(ammoMalValue);
		int ammoZahlfürCustom = 0;
		if (fenster.isCustomLevel())
			ammoZahlfürCustom = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.gegnerA));
		for (int i = 0; i < Settings.MUNITION_ANZAHL_AUFDEMFELD + ammoZahlfürCustom; i++)
			fenster.getDrm().addAmmo(new Ammo());

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
		// Brett vorbreiten
		allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "obstacle.gif");
				if (bonus != null)
					if (allePawns[i].getX() == bonus.getSpalte() && allePawns[i].getY() == bonus.getZeile()) {
						stopBlinking();
						hertzAddiert = false;
						System.out.println("...................");
					}
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

			} else if (allePawns[i] instanceof Ammo) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "ammo.png");
			}
		}

		// Hertz wird hinzugefuegt
		if (!hertzAddiert) {
			markiereAlleFelder();
			markiereBelegteFelder();
			bonus = new Bonus(fenster);
			// Min + (int)(Math.random() * ((Max - Min) + 1))
			// int rand = 1 + (int)(Math.random()*2); // random Zahl 1 or 2
			int randomZ = 1 + (int) (Math.random() * (zeilenA + 1));
			int randomS = 1 + (int) (Math.random() * (spaltenA + 1));

			// Bonus hinzufuegen
			for (int i = randomZ; i < zeilenA; i++) { // fuer Zeilen
				for (int j = randomS; j < spaltenA; j++) { // fuer Spalten
					if (leerFelder[j][i].getText().equals(".")) { // Feld ist
																	// leer
						bonus.setSpalte(j);
						bonus.setZeile(i);
						hertzAddiert = true;
						blinkingBonus("heart.png");
						startBlinking();
						return;
					}
				}
			}

		} else {
			setzeFigur(bonus.getSpalte(), bonus.getZeile(), "heart.png");
		}
	}

	public void blinkingBonus(String filename) {
		setzeFigur(bonus.getSpalte(), bonus.getZeile(), filename);

	}

	private void markiereBelegteFelder() {
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			} else if (allePawns[i] instanceof Opponent) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			} else if (allePawns[i] instanceof Player) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			} else if (allePawns[i] instanceof Vortex) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			} else if (allePawns[i] instanceof Destruction) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			} else if (allePawns[i] instanceof Ammo) {
				leerFelder[allePawns[i].getX()][allePawns[i].getY()].setText("x");
			}
		}
	}

	public void startBlinking() {
		if (!threadBonus.isAlive()) {
			threadBonus = new Thread(rha);
			threadBonus.start();
		}
	}

	public void deleteHertz() {
		setzeFigur(bonus.getSpalte(), bonus.getZeile(), "");
		System.out.println("hertz delete.........................");
	}

	public void stopBlinking() {
		if (threadBonus.isAlive()) {
			rha.terminate();
		}
	}

	public void bonusGewonnen() {
		stopBlinking();
		setHertzAddiert(false);
		System.out.println("Bonus getroffen");
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

	private void markiereAlleFelder() {
		// leere Felder markieren
		for (int i = 0; i < zeilenA; i++) { // fuer Zeilen
			for (int j = 0; j < spaltenA; j++) { // fuer Spalten
				leerFelder[j][i] = new JLabel("."); // Feld ist leer
			}
		}
	}

	private void initSpielFeld() {
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

	public JLabel[][] getLeerFelder() {
		return leerFelder;
	}

	public void setLeerFelder(JLabel[][] leerFelder) {
		this.leerFelder = leerFelder;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus _bonus) {
		bonus = _bonus;
	}

	public int getAmmoMalValue() {
		return ammoMalValue;
	}

	public JPanel getSpielFeld() {
		return this;
	}

	public void setTheme(String _theme) {
		theme = _theme;
	}

	public void setHertzAddiert(boolean sHA) {
		hertzAddiert = sHA;
	}

	public boolean isHertzAddiert() {
		return hertzAddiert;
	}

}
