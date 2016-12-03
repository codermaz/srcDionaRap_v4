import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

	private float panelBreite = SpielfeldEigenschaften.SPALTEN_ANZAHL * SpielfeldEigenschaften.LABEL_DIMENSION
			/ SpielfeldEigenschaften.TOOLBAR_PANEL_ANZAHL;
	private int panelHeight = SpielfeldEigenschaften.TOOLBAR_HEIGHT;
	private Dimension pDim = new Dimension((int) panelBreite, panelHeight);
//	Color PR_FARBE = new Color(0xD0DFEF);  // PanelRandfarbe himmelblau
	Color PR_FARBE = new Color(0x6DB45D);  // PanelRandfarbe grün

	private JButton bNeuSpiel = new JButton("Neues Spiel");
	private JButton bSettings = new JButton("Settings");
	private JTextField tPunkte = new JTextField("0");
	private JLabel lMunition[] = new JLabel[3];
	private JProgressBar pbSpielFortschritt = new JProgressBar();

	ToolBarMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;

		initToolBar();
		initPanelNeuSpiel();
		initPanelPunkteStand();
		initPanelMunition();
		initPanelFortschritt();
		initPanelSettings();
		
		// TODO check if works fine
		// setLayout(new GridLayout(1, 7));
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS)); 

		add(pNeuSpiel);
		add(new Box.Filler(new Dimension(0,0), new Dimension(3,3), new Dimension(6,3)));
		add(pPunkteStand);
		add(new Box.Filler(new Dimension(0,0), new Dimension(3,3), new Dimension(6,3)));
		add(pMunition);
		add(new Box.Filler(new Dimension(0,0), new Dimension(3,3), new Dimension(6,3)));
		add(pSpielFortschritt);
		add(new Box.Filler(new Dimension(0,0), new Dimension(3,3), new Dimension(6,3)));
		add(pSettings);

	}

	private void initPanelNeuSpiel() {
		bNeuSpiel.setActionCommand("NeuSpiel");
		bNeuSpiel.setEnabled(false);
		bNeuSpiel.setAlignmentX(Component.CENTER_ALIGNMENT);

		pNeuSpiel.setLayout(new BoxLayout(pNeuSpiel, BoxLayout.Y_AXIS));
		pNeuSpiel.setPreferredSize(pDim);

		pNeuSpiel.add(Box.createGlue());
		pNeuSpiel.add(bNeuSpiel);
		pNeuSpiel.add(Box.createGlue());
	}

	private void initPanelPunkteStand() {
		tPunkte.setAlignmentX(Component.CENTER_ALIGNMENT);

		pPunkteStand.setLayout(new BoxLayout(pPunkteStand, BoxLayout.Y_AXIS));

		pPunkteStand.setBorder(BorderFactory.createLineBorder(PR_FARBE));
		pPunkteStand.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		pPunkteStand.setPreferredSize(pDim);

		pPunkteStand.add(Box.createGlue());
		pPunkteStand.add(tPunkte);
		pPunkteStand.add(Box.createGlue());
	}

	private void initPanelMunition() {
		String fs= File.separator;
		String theme=SpielfeldEigenschaften.THEME;
		String figur = "ammo.png";
		ImageIcon image= new ImageIcon(System.getProperty("user.dir") 
				+ fs +"images" + fs + theme + fs + figur);
		
		for (int i = 0; i < 3; i++) {
			lMunition[i] = new JLabel();
			lMunition[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			lMunition[i].setIcon(image);
		}
		
		//pMunition.setBorder(BorderFactory.createLineBorder(PR_FARBE));
		pMunition.setBorder(BorderFactory.createTitledBorder("Munition"));
		pMunition.setLayout(new BoxLayout(pMunition, BoxLayout.X_AXIS));
		pMunition.setPreferredSize(pDim);
	
		pMunition.add(Box.createHorizontalGlue());
		for (int i = 0; i < 3; i++) {
			pMunition.add(lMunition[i]);
		}
		pMunition.add(Box.createHorizontalGlue());
		
	}

	private void initPanelFortschritt() {
		pbSpielFortschritt.setAlignmentX(Component.CENTER_ALIGNMENT);
		pSpielFortschritt.setLayout(new BoxLayout(pSpielFortschritt, BoxLayout.Y_AXIS));
	//	pSpielFortschritt.setBorder(BorderFactory.createLineBorder(PR_FARBE));
		pbSpielFortschritt.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));

		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.add(pbSpielFortschritt);
		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.setPreferredSize(pDim);
	}

	private void initPanelSettings() {
		bSettings.setActionCommand("Settings");
		bSettings.setEnabled(true);
		bSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

		pSettings.setLayout(new BoxLayout(pSettings, BoxLayout.Y_AXIS));
		pSettings.setPreferredSize(pDim);

		pSettings.add(Box.createGlue());
		pSettings.add(bSettings);
		pSettings.add(Box.createGlue());
	}

	private void initToolBar() {
		setPreferredSize(new Dimension(SpielfeldEigenschaften.SPALTEN_ANZAHL * SpielfeldEigenschaften.LABEL_DIMENSION,
				SpielfeldEigenschaften.TOOLBAR_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
}
