import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ToolBarMenu extends JPanel{

	private DionaRap_Hauptfenster fenster;
	private JToolBar tBar = new JToolBar();
	private JPanel pNeuSpiel = new JPanel();
	private JPanel pPunkteStand = new JPanel();
	private JPanel pMunition = new JPanel();
	private JPanel pSpielFortschritt = new JPanel();
	private JPanel pSettings = new JPanel();

	private float panelBreite = SpielBrettEigenschaften.SPALTEN_ANZAHL * SpielBrettEigenschaften.LABEL_DIMENSION
	 	/ SpielBrettEigenschaften.TOOLBAR_PANEL_ANZAHL;
	
	private int panelHeight = 30;
	
	private Dimension pDim = new Dimension ((int) panelBreite, panelHeight);
	
	private JButton bNeuSpiel = new JButton("Neues Spiel");
	private JButton bSettings = new JButton("Settings");
	private JTextField tPunkte = new JTextField();
	private JLabel lMunition[] = new JLabel[3];
	private JProgressBar pbSpielFortschritt = new JProgressBar();

	ToolBarMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		
		initToolBar();
		initPanelNeuSpiel();
		//initPanelPunkteStand();
		//initPanelMunition();
		//initPanelFortschritt();
		initPanelSettings();
		
		tBar.setLayout(new GridLayout(1,5));
		
		tBar.add(pNeuSpiel);
		tBar.add(pPunkteStand);
		tBar.add(pMunition);
		tBar.add(pSpielFortschritt);
		tBar.add(pSettings);
		
	}

	private void initPanelNeuSpiel() {
	
		//pNeuSpiel.setPreferredSize(pDim);
		pNeuSpiel.setSize(pDim);
		pNeuSpiel.setLayout(new BoxLayout(pNeuSpiel, BoxLayout.Y_AXIS));
		
		bNeuSpiel.setActionCommand("NeuSpiel");		
		//bNeuSpiel.setEnabled(false);
		bNeuSpiel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pNeuSpiel.add(Box.createGlue());
		pNeuSpiel.add(bNeuSpiel);
		pNeuSpiel.add(Box.createGlue());

		System.out.println(" pDim Width"+pNeuSpiel.getWidth()+" pDim Heigth"+pNeuSpiel.getHeight());
	}

	private void initPanelPunkteStand() {

	}

	private void initPanelMunition() {

	}

	private void initPanelFortschritt() {

	}

	private void initPanelSettings() {
		//pSettings.setPreferredSize(pDim);
		pSettings.setSize(pDim);
		pSettings.setLayout(new BoxLayout(pSettings, BoxLayout.Y_AXIS));
		bSettings.setActionCommand("Settings");
		
		bSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pSettings.add(Box.createGlue());
		pSettings.add(bSettings);
		pSettings.add(Box.createGlue());
	}

	private void initToolBar() {

		tBar.setSize(SpielBrettEigenschaften.SPALTEN_ANZAHL * SpielBrettEigenschaften.LABEL_DIMENSION,
			SpielBrettEigenschaften.TOOLBAR_HEIGHT);
	
	System.out.println("tBar xsize "+tBar.getSize().width+" ysize"+tBar.getSize().height);
	System.out.println("fenster xsize "+fenster.getSize().width+" ysize"+fenster.getSize().height);
	}

}
