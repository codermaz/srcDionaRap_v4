import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;



public class ToolBarMenu extends JToolBar {

	private static final long serialVersionUID = 1L;
	private DionaRap_Hauptfenster fenster;
	
	private JPanel pNeuSpiel = new JPanel();
	private JPanel pPunkteStand = new JPanel();
	private JPanel pMunition = new JPanel();
	private JPanel pSpielFortschritt = new JPanel();
	private JPanel pSettings = new JPanel();

	private float panelBreite;
	private int panelHeight = Settings.TOOLBAR_HEIGHT;
	private Dimension pDim;
	public Color PR_FARBE_blau = new Color(0x185BAF);  // PanelRandfarbe himmelblau
	public Color LR_FARBE_gruen = new Color(0x6DB45D); // LabelRandfarbe gruen
	public Color PR_FARBE_red = new Color(0xC13A55);  	// PanelRandfarbe red
	
	private Font panelFont = new Font("times new roman",Font.PLAIN,12);
	
	private JButton bNeuSpiel;
	private JButton bSettings = new JButton("Settings");
	private JTextField tPunkte = new JTextField("0");
	private JLabel lPunkte = new JLabel();
	public ArrayList<String> levelListe; //LevelListe
	private JLabel lMunition[] = new JLabel[3];
	private ImageIcon imageMunition;
	private int dimensionXLabelMunition=30;
	private int dimensionYLabelMunition=30;
	private JProgressBar pbSpielFortschritt = new JProgressBar(0,100);
	
	private RunnableBlinkingMunition rbm;
	private Thread threadBlinking;

	ToolBarMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		panelBreite = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA))
				* Settings.LABEL_DIMENSION
				/ Settings.TOOLBAR_PANEL_ANZAHL;
		pDim = new Dimension((int) panelBreite, panelHeight);
		initToolBar();
		
		initPanelNeuSpiel();
		initPanelPunkteStand();
		initPanelMunition();
		initPanelFortschritt();
		initPanelSettings();
		
		setLayout(new GridLayout(1, 5));
		add(pNeuSpiel);
		add(pPunkteStand);
		add(pMunition);
		add(pSpielFortschritt);
		add(pSettings);
		//runnable Instanz 
		rbm = new RunnableBlinkingMunition(this);
		//Thread erzeugen mit runnable Instanz 
		threadBlinking = new Thread(rbm);
	}
	
	public void blinkingMunition(Color farbe,int thickness) { 
		pMunition.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(farbe, thickness),
				"Munition",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont,farbe));
	}
	
	
	public void startBlinking() {
		if (!threadBlinking.isAlive()) {
			threadBlinking = new Thread(rbm);
			threadBlinking.start();
		}
		j=0;
	}
	int j=0;
	public void stopBlinking(){
		if (threadBlinking.isAlive()) {
			threadBlinking.interrupt();
			System.out.println("stop blinking:" +(++j));
		}
	}
	
	public JToolBar getToolBarMenu() {
		return this;
	}

	private void initPanelNeuSpiel() {
		bNeuSpiel = new JButton("Neues Spiel");
		bNeuSpiel.setActionCommand("NeuSpiel");
		bNeuSpiel.setEnabled(false);
		bNeuSpiel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bNeuSpiel.setFont(panelFont);
		bNeuSpiel.addActionListener(new ListenerToolBarButtons(fenster));
		
		pNeuSpiel.setLayout(new BoxLayout(pNeuSpiel, BoxLayout.Y_AXIS));
		pNeuSpiel.setPreferredSize(pDim);

		pNeuSpiel.add(Box.createGlue());
		pNeuSpiel.add(bNeuSpiel);
		pNeuSpiel.add(Box.createGlue());
	}
	
	public void setButtonNeuEnabled(Boolean b) {
		bNeuSpiel.setEnabled(b);
	}

	private void initPanelPunkteStand() {
		Dimension lDim1 = new Dimension(80,30);
		Dimension lDim2 = new Dimension(20,30);
		
		levelListe= new ArrayList<>();
        levelListe.add("Level A-1"); levelListe.add("Level A-2"); levelListe.add("Level A-3");
        levelListe.add("Level B-1"); levelListe.add("Level B-2"); levelListe.add("Level B-3");
        levelListe.add("Level C-1"); levelListe.add("Level C-2"); levelListe.add("Level C-3");
        levelListe.add("Custom Level");
    	if (fenster.isCustomLevel())
			lPunkte.setText("Custom Level");
		else 
			lPunkte.setText(levelListe.get(fenster.currentLevel));
        lPunkte.setFont(panelFont);
        lPunkte.setHorizontalAlignment(JLabel.CENTER);
        lPunkte.setPreferredSize(lDim1);
        //lPunkte.setAlignmentX(Component.LEFT_ALIGNMENT);

		tPunkte.setEditable(false);
		tPunkte.setColumns(5);
		tPunkte.setHorizontalAlignment(JTextField.CENTER);
		tPunkte.setForeground(PR_FARBE_blau);
		tPunkte.setPreferredSize(lDim2);
		tPunkte.setText(Integer.toString(fenster.currentPunkte));
        
		pPunkteStand.setLayout(new BoxLayout(pPunkteStand, BoxLayout.X_AXIS));
		pPunkteStand.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(PR_FARBE_blau, 1),
				"Punktestand",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont, PR_FARBE_blau));

		pPunkteStand.setPreferredSize(pDim);
		pPunkteStand.add(lPunkte);
		pPunkteStand.add(tPunkte);
		
	}
	
	public void setPunkteStand(String Punkte) {
		if (fenster.isCustomLevel())
			lPunkte.setText("Custom");
		else 
			lPunkte.setText(levelListe.get(fenster.currentLevel));
		tPunkte.setText(Punkte);
	}

	public void initPanelMunition() {
		String fs= File.separator;
		String theme=Settings.THEME;
		String figur = "ammo.png";
		
		//redimension for Icon
		lMunition[0] = new JLabel();
		lMunition[0].setPreferredSize(new Dimension(dimensionXLabelMunition,dimensionYLabelMunition));
		imageMunition= new ImageIcon(System.getProperty("user.dir") 
				+ fs +"images" + fs + theme + fs + figur) {
		private static final long serialVersionUID = 1L;

			//Resize the icon to fit a JLabel
			@Override
			public void paintIcon( Component c, Graphics g, int x, int y ) {
				g.drawImage(getImage(), 0, 0, lMunition[0].getWidth(),lMunition[0].getHeight(), c);
			}
		};

		for (int i = 0; i < 3; i++) {
			lMunition[i] = new JLabel();
			lMunition[i].setIcon(imageMunition);
			lMunition[i].setBorder(BorderFactory.createLineBorder(LR_FARBE_gruen));
		}
		pMunition.setLayout(new GridLayout(1,3,2,2));

		pMunition.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(PR_FARBE_blau, 1),
				"Munition",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont, PR_FARBE_blau));
		pMunition.setPreferredSize(pDim);
		pMunition.setToolTipText("aktueller Munitionsvorrat");
		
		pMunition.removeAll();
		for (int i = 0; i < 3; i++) 
			pMunition.add(lMunition[i]);
		showMunitionAnzahl(fenster.getDrm().getShootAmount());
		
	}

	public void showMunitionAnzahl(int Anzahl) {
		if ( (Anzahl==Settings.MUNITION_ANZAHL_FUREINAMMO) && (threadBlinking.isAlive()) )
			stopBlinking();

		//Munitionsanzeige entleeren
		for (int i = 0; i < 3; i++) 
			lMunition[i].setIcon(null);
		lMunition[0].setText(null);
		
		//Munitionsanzeige wieder fuellen
		switch (Anzahl) {
		case 3: 
			lMunition[0].setIcon(imageMunition);
		case 2:
			lMunition[1].setIcon(imageMunition);
		case 1:
			lMunition[2].setIcon(imageMunition);
			break;
		}
		if (Anzahl>3) {
			lMunition[0].setIcon(null);
			lMunition[0].setText("*"+Integer.toString(Anzahl));
			lMunition[0].setHorizontalAlignment(SwingConstants.CENTER); 
			for (int i = 1; i < 3; i++) 
				lMunition[i].setIcon(imageMunition);
		}

		pMunition.updateUI();  //###
	}
	
	private void initPanelFortschritt() {
		pbSpielFortschritt.setAlignmentX(Component.CENTER_ALIGNMENT);
		pSpielFortschritt.setLayout(new BoxLayout(pSpielFortschritt, BoxLayout.Y_AXIS));
		pSpielFortschritt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(PR_FARBE_blau, 1),
				"Spielfortschritt",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont, PR_FARBE_blau));

		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.add(pbSpielFortschritt);
		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.setPreferredSize(pDim);
		
		pbSpielFortschritt.setStringPainted(true);
	}
	
	public void setSpielfortschritWert(int Wert) {
		pbSpielFortschritt.setValue(Wert);
	}

	private void initPanelSettings() {
		bSettings.setActionCommand("Settings");
		bSettings.setEnabled(true);
		bSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		bSettings.setFont(panelFont);
		bSettings.addActionListener(new ListenerToolBarButtons(fenster));
		
		pSettings.setLayout(new BoxLayout(pSettings, BoxLayout.Y_AXIS));
		pSettings.setPreferredSize(pDim);

		pSettings.add(Box.createGlue());
		pSettings.add(bSettings);
		pSettings.add(Box.createGlue());
	}

	private void initToolBar() {
		setPreferredSize(new Dimension(Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA))
				* Settings.LABEL_DIMENSION,	Settings.TOOLBAR_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setFloatable(false);
	}
	
}
