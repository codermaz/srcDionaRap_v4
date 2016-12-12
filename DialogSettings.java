import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogSettings extends JDialog {

	DionaRap_Hauptfenster fenster;
	private JPanel pSettings = new JPanel();
	private JLabel[] labels = new JLabel[12];
	private JTextField[] texts = new JTextField[12];
	private Dimension prefSize = new Dimension(250,30);

	DialogSettings(DionaRap_Hauptfenster _fenster) {
	
		super(_fenster, "Settings");
		fenster = _fenster;
		initAktuellSettings();
	}

	private void initAktuellSettings() {
		this.setLayout(new GridLayout(12, 2));

		initLabels();
		initTexts();

		this.add(pSettings);
		this.setSize(500, 400);
		this.setModal(true);
		//this.setResizable(false);
		this.setLocationRelativeTo(fenster);
		this.pack();
		this.setVisible(true);

	}

	private void initTexts() {
		for (int i = 0; i < 11; i++) {
			texts[i] = new JTextField();
			texts[i].setPreferredSize(prefSize);
			texts[i].setText("-----------");
			pSettings.add(labels[i]);
			pSettings.add(texts[i]);
		}

	}

	private void initLabels() {
		for (int i = 0; i < 11; i++) {
			labels[i] = new JLabel();
			labels[i].setPreferredSize(prefSize);
		}
		labels[0].setText("Wartezeit der Gegner zu Beginn");
		labels[1].setText("Verzögerung eines Schusses");
		labels[2].setText("Wartezeit eines Gegners vor jedem Schnritt");
		labels[3].setText("");
		labels[4].setText("");
		labels[5].setText("");
		labels[6].setText("Anzahl Zeilen des Spielfelds");
		labels[7].setText("Anzahl Spalten des Spielfelds");
		labels[8].setText("Anzahl Hindernisse");
		labels[9].setText("Anzahl der Gegner");
		labels[10].setText("Schwierigkeitsstufe");

	}

}
