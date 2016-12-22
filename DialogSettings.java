import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DialogSettings extends JDialog implements ChangeListener, FocusListener, ItemListener {
	/*
	 * ActionListener : für Button/ ChangeListener : für Slider / ItemListener :
	 * für Checkbox/ TextEvent, FocusListener : für TextField  
	 */

	private static final long serialVersionUID = 1L;

	private DionaRap_Hauptfenster fenster;
	private Dimension prefSize = new Dimension(250, 30);
	private ListenerSettingsDialog lSettings;
	private JPanel pSettings = new JPanel();
	private JLabel[] labels = new JLabel[12];
	private JSlider jsoStartWT, jsoWaitT, jssWaitT;
	private JCheckBox jcheck[] = new JCheckBox[4];
	private JTextField[] texts = new JTextField[12];
	private JComboBox<String> jcbLevel;
	private JPanel pButtons;
	private HashMap<String, String> neuEinstellungen;

	public boolean soundChanged;
  

	DialogSettings(DionaRap_Hauptfenster _fenster) {

		super(_fenster, "Settings");
		fenster = _fenster;
		initAktuellSettings();
	}

	
	private void initAktuellSettings() {
		lSettings = new ListenerSettingsDialog(fenster, this);
		fenster.getController().deactivateMultiThreading();
		neuEinstellungen = ((HashMap<String, String>) fenster.getSettings().getEinstellungen().clone());

		pSettings.setLayout(new GridLayout(11, 2, 5, 7));

		initLabels();
		initSliders();
		initCheckBoxes();
		initTextFields();
		initComboBox();
		addComponentsToPanel();
		this.add(pSettings, BorderLayout.CENTER);

		initButtons();
		this.add(pButtons, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(550, 600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		// this.setLocationRelativeTo(fenster);
		this.setLocation(fenster.getX(), fenster.getY());
		this.pack();
		this.setVisible(true);

	}

	private void initComboBox() {
	     // Array für unsere JComboBox
        jcbLevel= new JComboBox<String>(); 
        jcbLevel.setModel(new DefaultComboBoxModel (fenster.getToolBarMenu().levelListe.toArray()));//###
        jcbLevel.setActionCommand("Level");
        int itemNo= Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.levelS)); 
        jcbLevel.setSelectedIndex(itemNo);
        jcbLevel.addActionListener(lSettings);
	}

	private void addComponentsToPanel() {

		// JSlider Group
		pSettings.add(labels[0]);
		pSettings.add(jsoStartWT);
		pSettings.add(labels[1]);
		pSettings.add(jssWaitT);
		pSettings.add(labels[2]);
		pSettings.add(jsoWaitT);
		// JCehckBox Group
		pSettings.add(jcheck[0]);
		pSettings.add(jcheck[1]);
		pSettings.add(jcheck[2]);
		pSettings.add(jcheck[3]);
		// JTextField Group
		pSettings.add(labels[6]);
		pSettings.add(texts[6]);
		pSettings.add(labels[7]);
		pSettings.add(texts[7]);
		pSettings.add(labels[8]);
		pSettings.add(texts[8]);
		pSettings.add(labels[9]);
		pSettings.add(texts[9]);

		// JComboBoxModel
		pSettings.add(labels[10]);
		pSettings.add(jcbLevel);
		
	}

	public void updateWerte(HashMap <String, String> updEinstellungen) {
		jsoStartWT.setValue(Integer.parseInt(updEinstellungen.get(Settings.oStartWT)));
		jsoWaitT.setValue(Integer.parseInt(updEinstellungen.get(Settings.oWaitT)));
		jssWaitT.setValue(Integer.parseInt(updEinstellungen.get(Settings.sWaitT)));
		jcheck[0].setSelected(Boolean.parseBoolean(updEinstellungen.get(Settings.rOppWT)));
		jcheck[1].setSelected(Boolean.parseBoolean(updEinstellungen.get(Settings.aColWObs)));
		jcheck[2].setSelected(Boolean.parseBoolean(updEinstellungen.get(Settings.aColWOpp)));
		jcheck[3].setSelected(Boolean.parseBoolean(updEinstellungen.get(Settings.sGetsOT)));
		texts[6].setText(updEinstellungen.get(Settings.zeilenA));
		texts[7].setText(updEinstellungen.get(Settings.spaltenA));
		texts[8].setText(updEinstellungen.get(Settings.gegnerA));
		texts[9].setText(updEinstellungen.get(Settings.hindernisA));
		jcbLevel.setSelectedIndex(Integer.parseInt(updEinstellungen.get(Settings.levelS)));
		neuEinstellungen = updEinstellungen;
	}
	
	private void initCheckBoxes() {
		for (int i = 0; i < 4; i++) {
			jcheck[i] = new JCheckBox();
			jcheck[i].setPreferredSize(prefSize);
			jcheck[i].setName(Integer.toString(i));
			jcheck[i].addItemListener(this);
		}
		jcheck[0].setText("Zufällige Wartezeit der Gegner");
		jcheck[0].setSelected(Boolean.parseBoolean(neuEinstellungen.get(Settings.rOppWT)));
		jcheck[1].setText("Gegner meiden Kollision mit Hindernis");
		jcheck[1].setSelected(Boolean.parseBoolean(neuEinstellungen.get(Settings.aColWObs)));
		jcheck[2].setText("Gegner meiden Kollision mit anderen Gegnern");
		jcheck[2].setSelected(Boolean.parseBoolean(neuEinstellungen.get(Settings.aColWOpp)));
		jcheck[3].setText("nicht unbegrenzte Anzahl Schüsse");
		jcheck[3].setSelected(Boolean.parseBoolean(neuEinstellungen.get(Settings.sGetsOT)));

	}

	private void initSliders() {
		jsoStartWT = new JSlider();
		int v = Integer.parseInt(neuEinstellungen.get(Settings.oStartWT));
		erstelleJSlider(jsoStartWT, Settings.oStartWT, v);
		jssWaitT = new JSlider();
		erstelleJSlider(jssWaitT, Settings.sWaitT, Integer.parseInt(neuEinstellungen.get(Settings.sWaitT)));
		jsoWaitT = new JSlider();
		erstelleJSlider(jsoWaitT, Settings.oWaitT, Integer.parseInt(neuEinstellungen.get(Settings.oWaitT)));
	}

	private void erstelleJSlider(JSlider js, String jsName, int value) {
		js.setName(jsName);
		js.setPreferredSize(prefSize);
		js.addChangeListener(this);
		// Orientation
		js.setOrientation(JSlider.HORIZONTAL);
		// Mindestwert wird gesetzt
		js.setMinimum(0);
		// Maximalwert wird gesetzt
		js.setMaximum(10000);
		// Die Abstände zwischen den Teilmarkierungen werden festgelegt
		js.setMajorTickSpacing(2000);
		js.setMinorTickSpacing(500);
		// Standardmarkierungen werden erzeugt
		js.createStandardLabels(1);
		// Zeichnen der Markierungen wird aktiviert
		js.setPaintTicks(true);
		// Zeichnen der Labels wird aktiviert
		js.setPaintLabels(true);
		// Schiebebalken wird auf den Wert "value" gesetzt
		js.setValue(value); // <***>
	}

	// für JSlider
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		int value = source.getValue();
		fenster.setCustomLevel(true);
		if (source.getName().equals(Settings.oStartWT)) {
			neuEinstellungen.put(Settings.oStartWT, Integer.toString(value));
		} else if (source.getName().equals(Settings.oWaitT)) {
			neuEinstellungen.put(Settings.oWaitT, Integer.toString(value));
		} else if (source.getName().equals(Settings.sWaitT)) {
			neuEinstellungen.put(Settings.sWaitT, Integer.toString(value));
		}
    	// TODO ?Fehler NullPointer
//		jcbLevel.setSelectedIndex(9);
	}

	private void initButtons() {
		pButtons = new JPanel();
		JButton bOK = new JButton("OK");
		bOK.setPreferredSize(new Dimension(120, 25));
		bOK.addActionListener(lSettings);
		JButton bAbbrechen = new JButton("Abbrechen");
		bAbbrechen.setPreferredSize(new Dimension(120, 25));
		bAbbrechen.addActionListener(lSettings);
		pButtons.add(bOK);
		pButtons.add(bAbbrechen);

	}

	private void initTextFields() {
		for (int i = 6; i < 10; i++) {
			texts[i] = new JTextField();
			texts[i].setPreferredSize(prefSize);
			texts[i].setName(Integer.toString(i));
			texts[i].addFocusListener(this);
		}
		texts[6].setText(neuEinstellungen.get(Settings.zeilenA));
		texts[7].setText(neuEinstellungen.get(Settings.spaltenA));
		texts[8].setText(neuEinstellungen.get(Settings.gegnerA));
		texts[9].setText(neuEinstellungen.get(Settings.hindernisA));
	}

	private void initLabels() {
		for (int i = 0; i < 11; i++) {
			labels[i] = new JLabel();
			labels[i].setPreferredSize(prefSize);
		}
		labels[0].setText("Wartezeit der Gegner zu Beginn");
		labels[1].setText("Verzögerung eines Schusses");
		labels[2].setText("Wartezeit eines Gegners vor jedem Schritt");
		labels[3].setText(" ");
		labels[4].setText(" ");
		labels[5].setText(" ");
		labels[6].setText("Anzahl Zeilen des Spielfelds");
		labels[7].setText("Anzahl Spalten des Spielfelds");
		labels[8].setText("Anzahl Hindernisse");
		labels[9].setText("Anzahl der Gegner");
		labels[10].setText("Schwierigkeitsstufe");

	}

	public void performNeuEinstellungen() {
		fenster.getSettings().setEinstellungen(neuEinstellungen);
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox jcBox = (JCheckBox) e.getSource();
		String sJCBox = Boolean.toString(jcBox.isSelected());
		int icBox = Integer.parseInt(jcBox.getName());
		fenster.setCustomLevel(true);
		switch (icBox) {
		case 0: // "Zufällige Wartezeit der Gegner"
			neuEinstellungen.put(Settings.rOppWT, sJCBox);
			break;
		case 1: // "Gegner meiden Kollision mit Hindernis"
			neuEinstellungen.put(Settings.aColWObs, sJCBox);
			break;
		case 2: // "Gegner meiden Kollision mit anderen Gegnern"
			neuEinstellungen.put(Settings.aColWOpp, sJCBox);
			break;
		case 3: // "nicht unbegrenzte Anzahl Schüsse"
			neuEinstellungen.put(Settings.sGetsOT, sJCBox);
			break;
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) { 
		fenster.setCustomLevel(true);
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField jtext = (JTextField) e.getSource();
		int itext = Integer.parseInt(jtext.getName());

		switch (itext) {
		case 6: // "Anzahl Zeilen des Spielfelds
			neuEinstellungen.put(Settings.zeilenA, jtext.getText());
			break;
		case 7: // "Anzahl Spalten des Spielfelds
			neuEinstellungen.put(Settings.spaltenA, jtext.getText());
			break;
		case 8: // "Anzahl Hindernisse
			neuEinstellungen.put(Settings.hindernisA, jtext.getText());
			break;
		case 9: // "Anzahl der Gegner
			neuEinstellungen.put(Settings.gegnerA, jtext.getText());
			break;
		}
	}

}
