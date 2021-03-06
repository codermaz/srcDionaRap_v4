import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import de.fhwgt.dionarap.model.data.MTConfiguration;

public class ListenerSettingsDialog implements ActionListener {

	private DionaRap_Hauptfenster fenster;
	private DialogSettings dSettings;
	private MTConfiguration conf;

	public ListenerSettingsDialog(DionaRap_Hauptfenster _fenster, DialogSettings _dSettings) {
		fenster = _fenster;
		dSettings = _dSettings;
		conf = fenster.getConf();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Befehl = e.getActionCommand();

		if ("Level".equals(Befehl)) {  //ComboBox
			if (!(e.getSource() instanceof JComboBox<?>)) {
				return;
			}
			JComboBox<String> jcb = (JComboBox<String>) e.getSource();
			fenster.getSettings().setMapMTKonfig(jcb.getSelectedIndex());
			fenster.currentLevel= jcb.getSelectedIndex();
			dSettings.updateWerte(fenster.getSettings().getEinstellungen());
			fenster.setCustomLevel(false);		
		} else {	//Buttons
			dSettings.dispose();
			fenster.getController().setMultiThreaded(conf);

			if ("OK".equals(Befehl)) {
				dSettings.performNeuEinstellungen();
				fenster.spielStart();
			}
		}

	}

}
