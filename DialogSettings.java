import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DialogSettings extends JDialog implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DionaRap_Hauptfenster fenster;
	private JPanel pSettings = new JPanel();
	private JLabel[] labels = new JLabel[12];
	private JTextField[] texts = new JTextField[12];
	private Dimension prefSize = new Dimension(250, 30);
	private ListenerSettingsDialog lSettings;
	private JPanel pButtons;
	private JSlider jsoStartWT, jsoWaitT, jssWaitT;
	private JCheckBox c;
	private HashMap<String, String> neuEinstellungen;

	/*
	 * ActionListener : für Button, ChangeListener : für Slider
	 * ItemListener : für Checkbox, FocusListener : für TextField
	 */

	DialogSettings(DionaRap_Hauptfenster _fenster) {

		super(_fenster, "Settings");
		fenster = _fenster;
		initAktuellSettings();
	}

	private void initAktuellSettings() {
		lSettings = new ListenerSettingsDialog(fenster, this);
		fenster.getController().deactivateMultiThreading();
		neuEinstellungen = ((HashMap<String, String>) fenster.getSettings().getEinstellungen().clone());
//		neuEinstellungen = fenster.getSettings().getEinstellungen();

		pSettings.setLayout(new GridLayout(11, 2, 5, 7));

		initLabels();
		initTexts();
		initSliders();
		initCheckBoxes();

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

	private void addComponentsToPanel() {

		// pSettings.add(labels[0]);
		pSettings.add(labels[0]);
		pSettings.add(jsoStartWT);
		pSettings.add(labels[1]);
		pSettings.add(jsoWaitT);
		pSettings.add(labels[2]);
		pSettings.add(jssWaitT);
		
		pSettings.add(labels[3]);
		pSettings.add(texts[3]);
		pSettings.add(labels[4]);
		pSettings.add(texts[4]);
		pSettings.add(labels[5]);
		pSettings.add(texts[5]);
		pSettings.add(labels[6]);
		pSettings.add(texts[6]);
		pSettings.add(labels[7]);
		pSettings.add(texts[7]);
		pSettings.add(labels[8]);
		pSettings.add(texts[8]);
		pSettings.add(labels[9]);
		pSettings.add(texts[9]);
		pSettings.add(labels[10]);
		pSettings.add(texts[10]);
		
		
	}

	private void initCheckBoxes() {

	}

	private void initSliders() {
		jsoStartWT = new JSlider();
		int v= Integer.parseInt(neuEinstellungen.get(Settings.oStartWT));
		erstelleJSlider(jsoStartWT, Settings.oStartWT, v );
		jsoWaitT = new JSlider();
		erstelleJSlider(jsoWaitT, Settings.oWaitT, Integer.parseInt(neuEinstellungen.get(Settings.oWaitT)));
		jssWaitT = new JSlider();
		erstelleJSlider(jssWaitT, Settings.sWaitT, Integer.parseInt(neuEinstellungen.get(Settings.sWaitT)));

		System.out.println(Settings.oStartWT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.oStartWT)));
		System.out.println(Settings.oWaitT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.oWaitT)));
		System.out.println(fenster.getSettings().sWaitT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.sWaitT)));
	}

	private void erstelleJSlider(JSlider js, String jsName, int value) {
		js.setName(jsName);
		js.setPreferredSize(prefSize);
		js.addChangeListener(this);
		//Orientation
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
		js.setValue(value);
	}

    //für JSlider
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        int value = source.getValue();
 
        if(source.getName().equals(Settings.oStartWT)){          
            neuEinstellungen.put(Settings.oStartWT, Integer.toString(value));
    		System.out.println(Settings.oStartWT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.oStartWT)));
        }
        if(source.getName().equals(Settings.oWaitT)){
            neuEinstellungen.put(Settings.oWaitT, Integer.toString(value));
    		System.out.println(Settings.oWaitT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.oWaitT)));

        }
        if(source.getName().equals(Settings.sWaitT)){
            neuEinstellungen.put(Settings.sWaitT, Integer.toString(value));
    		System.out.println(Settings.sWaitT+">>"+Integer.parseInt(neuEinstellungen.get(Settings.sWaitT)));

        } 
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

	private void initTexts() {
		for (int i = 0; i < 11; i++) {
			texts[i] = new JTextField();
			texts[i].setPreferredSize(prefSize);
			texts[i].setText("->"+i);
		}

//		pSettings.remove(texts[10]);
//		pSettings.remove(texts[9]);

	}

	private void initLabels() {
		for (int i = 0; i < 11; i++) {
			labels[i] = new JLabel();
			labels[i].setPreferredSize(prefSize);
		}
		labels[0].setText("Wartezeit der Gegner zu Beginn");
		labels[1].setText("Verzögerung eines Schusses");
		labels[2].setText("Wartezeit eines Gegners vor jedem Schnritt");
		labels[3].setText(" ");
		labels[4].setText(" ");
		labels[5].setText(" ");
		labels[6].setText("Anzahl Zeilen des Spielfelds");
		labels[7].setText("Anzahl Spalten des Spielfelds");
		labels[8].setText("Anzahl Hindernisse");
		labels[9].setText("Anzahl der Gegner");
		labels[10].setText("Schwierigkeitsstufe");

	}

}
