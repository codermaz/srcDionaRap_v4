
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
	private Spielfeld spielFeld;
	private Settings settings;

	public DionaRap_Hauptfenster(String toolbarLocation, Point fensterLocation) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DionaRap");
		setResizable(false);

		//Settings 
		settings= new Settings();
		
		// Model und Controller
		drm = new DionaRapModel(settings.ZEILEN_ANZAHL, settings.SPALTEN_ANZAHL, settings.GEGNER_ANZAHL,
				settings.HINDERNIS_ANZAHL);
		controller = new DionaRapController(drm);
		listenerModel = new ListenerModel(this);
		drm.addModelChangedEventListener(listenerModel);

		// Multithreading
		controller.setMultiThreaded(new MultiThreadKonfiguration(this).getMTKonfiguration());
		drm.setShootAmount(Settings.MUNITION_ANZAHL);
		for (int i = 0; i < Settings.MUNITION_ANZAHL; i++)
			drm.addAmmo(new Ammo());
		drm.setAmmoValue(Settings.MUNITION_ANZAHL);
		// TODO ?AmmoValue stimmt nicht
		System.out.println("anfang muniton:" + drm.getAmmoValue());

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

		// für KeyListener
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
		new DionaRap_Hauptfenster(toolbarLocation, fensterLocation);

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
		this("Oben", null);
	}

	public static void main(String[] args) {
		new DionaRap_Hauptfenster();

	}
}
