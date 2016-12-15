
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.model.objects.Ammo;

public class DionaRap_Hauptfenster extends JFrame {

	/** 
	 *  
	 */
	// TODO KeyListener Nummer 5 nicht funktioniert

	private static final long serialVersionUID = 1L;

	private Navigator navisFenster;
	private MenuLeiste menuLeiste;
	private ToolBarMenu toolBarMenu;
	private String toolbarLocation = "Oben";
	private Point fensterLocation = null;

	private DionaRapModel drm;
	private DionaRapController controller;
	private ListenerModel listenerModel;
	private MTConfiguration conf;

	private Spielfeld spielFeld;
	private Settings settings;

	public DionaRap_Hauptfenster(String toolbarLocation, Point fensterLocation, HashMap<String, String> einstellungen) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DionaRap");
		setResizable(false);

		// Settings
		settings = new Settings(einstellungen);

		// Model und Controller
		drm = new DionaRapModel(Integer.parseInt(settings.getEinstellungen().get(Settings.zeilenA)),
				Integer.parseInt(settings.getEinstellungen().get(Settings.spaltenA)),
				Integer.parseInt(settings.getEinstellungen().get(Settings.gegnerA)),
				Integer.parseInt(settings.getEinstellungen().get(Settings.hindernisA)));
		controller = new DionaRapController(drm);
		listenerModel = new ListenerModel(this);
		drm.addModelChangedEventListener(listenerModel);

		// Multithreading
		conf = new MultiThreadKonfiguration(this).getMTKonfiguration();
		controller.setMultiThreaded(conf);

		// ShootAmount : Legt fest, wieviele Schüsse der Spieler zu Beginn hat.
		// -1 steht für eine unbegrenzte Anzahl an Munition.
		drm.setShootAmount(Settings.MUNITION_ANZAHL_ZUBEGIN);
		// AmmoValue : Setzt die Anzahl der Munition für ein Ammo-Objekt
		drm.setAmmoValue(Settings.MUNITION_ANZAHL_FUREINAMMO);
		for (int i = 0; i < Settings.MUNITION_ANZAHL_AUFDEMFELD; i++)
			drm.addAmmo(new Ammo());

		// Brett initialisieren
		spielFeld = new Spielfeld(this);
		add(spielFeld.getSpielBrett(), BorderLayout.CENTER);

		// Navigator initialisieren
		navisFenster = new Navigator(this);
		this.addComponentListener(new ListenerSpielBrett(navisFenster));
		spielFeld.setzeAllePawns();

		// Menuleiste initialisieren
		menuLeiste = new MenuLeiste(this);
		this.setJMenuBar(menuLeiste);

		// ToolBar initialisieren
		toolBarMenu = new ToolBarMenu(this);
		setToolbarPosition(toolbarLocation);

		pack();
		if (fensterLocation != null)
			setLocation(fensterLocation);
		else
			setLocationRelativeTo(null);
		setVisible(true);

		// fuer KeyListener
		this.requestFocus();
		addWindowFocusListener();
	}

	private void addWindowFocusListener() {
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				DionaRap_Hauptfenster.this.requestFocus(); // ***
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
		});
	}

	public void spielStart() {
		navisFenster.dispose();
		this.dispose();
		controller.deactivateMultiThreading();
		fensterLocation = getLocation();
		new DionaRap_Hauptfenster(toolbarLocation, fensterLocation, settings.getEinstellungen());

	}

	public void navigatorSichtWechsel() {
		navisFenster.setVisible(!navisFenster.isVisible());
	}

	public void setLookAndFeel(String lookAndFeel) {
		try {

			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
			SwingUtilities.updateComponentTreeUI(this.navisFenster);
			pack();
		} catch (Exception e) {
			System.out.println("setLookAndFeel Exception " + e);
		}

	}

	public MTConfiguration getConf() {
		return conf;
	}

	public void setConf(MTConfiguration conf) {
		this.conf = conf;
	}

	public DionaRapModel getDrm() {
		return drm;
	}

	public DionaRapController getController() {
		return controller;
	}

	public Settings getSettings() {
		return settings;
	}

	public Spielfeld getSpielfeld() {
		return spielFeld;
	}

	public void setToolbarPosition(String aktionBefehl) {
		toolbarLocation = aktionBefehl;
		if (toolbarLocation == "Oben")
			add(toolBarMenu, BorderLayout.NORTH);
		else
			add(toolBarMenu, BorderLayout.SOUTH);
	}

	public ToolBarMenu getToolBarMenu() {
		return toolBarMenu;
	}

	DionaRap_Hauptfenster() {
		this("Oben", null, new HashMap<String, String>());
	}

	public static void main(String[] args) {
		new DionaRap_Hauptfenster();

	}
}
