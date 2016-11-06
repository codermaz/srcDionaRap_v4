

import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

public class NavigationsFenster extends JWindow {
	private Tastatur tast;
	private JPanel panelMitRand = new JPanel();

	Container navigationsFlaeche = this.getContentPane();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der Klasse <br>
	 * <br>
	 * Die Groesse wird fest eingestellt. Die Position relativ zu fenster wird
	 * berechnet. Das NavigationsFenster enhält ein Panel mit einem Rand - damit
	 * wird dem NavigationsFenster ein Rand gegeben
	 * 
	 * @param fenster
	 *            Das Vaterfenster zu diesem Object
	 */

	public NavigationsFenster(SpielBrett fenster) {
		super(fenster); // JWindow ist Kindfester zum JFrame. Es entfällt ein
						// Windowlistener !

		this.setLocation((int) fenster.getLocation().getX() + fenster.getWidth()
				+ SpielBrettEigenschaften.ENTFERNUNG_ZUM_SPIELBRETT, (int) fenster.getLocation().getY());

		tast = new Tastatur(fenster, panelMitRand, Color.red);

		navigationsFlaeche.add(panelMitRand);
		this.pack();
		this.setVisible(true);

		super.requestFocus();
		

		// tastaturErnuernMitSharp();

	}

	/* nicht für Übung 1 
	private void tastaturErnuernMitSharp() {
		String[] buttonText = new String[9];
		for (int i = 0; i < 9; i++)
			buttonText[i] = "#" + Integer.toString(i + 1);
		buttonText[4] = "*";
		tast.setTastatur(buttonText);
		
		navigationsFlaeche.add(panelMitRand);
		this.pack();
		this.setVisible(true);

	}
	*/
}
