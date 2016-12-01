import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ToolBarMenu extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private DionaRap_Hauptfenster fenster;
	private JPanel pNeuSpiel = new JPanel();
	private JPanel pPunkteStand = new JPanel();
	private JPanel pMunition = new JPanel();
	private JPanel pSpielFortschritt = new JPanel();
	private JPanel pSettings = new JPanel();

	private float panelBreite = SpielBrettEigenschaften.SPALTEN_ANZAHL * SpielBrettEigenschaften.LABEL_DIMENSION
			/ SpielBrettEigenschaften.TOOLBAR_PANEL_ANZAHL;

	private int panelHeight = 30;

	private Dimension pDim = new Dimension((int) panelBreite, panelHeight);

	private JButton bNeuSpiel = new JButton("Neues Spiel");
	private JButton bSettings = new JButton("Settings");
	private JTextField tPunkte = new JTextField();
	private JLabel lMunition[] = new JLabel[3];
	private JProgressBar pbSpielFortschritt = new JProgressBar();

	ToolBarMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;

		initToolBar();
		initPanelNeuSpiel();
		initPanelSettings();
		// initPanelPunkteStand();
		// initPanelMunition();
		// initPanelFortschritt();
		
		// TODO check if works fine
//		tBar.setLayout(new GridLayout(1, 5));

		add(pNeuSpiel);
		add(pSettings);
		/*
		 * tBar.add(pPunkteStand); tBar.add(pMunition);
		 * tBar.add(pSpielFortschritt);
		 */
	}

	private void initPanelNeuSpiel() {

		pNeuSpiel.setPreferredSize(pDim);
		// pNeuSpiel.setPreferredSize(pDim);
		bNeuSpiel.setActionCommand("NeuSpiel");
		// bNeuSpiel.setEnabled(false);
//		bNeuSpiel.setAlignmentX(Component.CENTER_ALIGNMENT);
//		bNeuSpiel.setAlignmentY(Component.CENTER_ALIGNMENT);

//		pNeuSpiel.add(Box.createGlue());
		pNeuSpiel.add(bNeuSpiel);
//		pNeuSpiel.add(Box.createGlue());

		System.out.println(" pDim Width" + pNeuSpiel.getWidth() + " pDim Heigth" + pNeuSpiel.getHeight());
	}

	private void initPanelPunkteStand() {

	}

	private void initPanelMunition() {

	}

	private void initPanelFortschritt() {

	}

	private void initPanelSettings() {
		// pSettings.setPreferredSize(pDim);
		pSettings.setPreferredSize(pDim);

		bSettings.setActionCommand("Settings");
		bSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		bSettings.setAlignmentY(Component.CENTER_ALIGNMENT);

		// pSettings.add(Box.createGlue());
		pSettings.add(bSettings);
		// pSettings.add(Box.createGlue());
	}

	private void initToolBar() {
		setPreferredSize(
				new Dimension(SpielBrettEigenschaften.SPALTEN_ANZAHL * SpielBrettEigenschaften.LABEL_DIMENSION,
						SpielBrettEigenschaften.TOOLBAR_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		System.out.println("tBar xsize " + getPreferredSize().width + " ysize" + getPreferredSize().height);
		System.out.println("fenster xsize " + fenster.getPreferredSize().width + " ysize" + fenster.getPreferredSize().height);
	}
}
