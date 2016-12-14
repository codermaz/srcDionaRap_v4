import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhwgt.dionarap.model.data.MTConfiguration;

public class ListenerSettingsDialog implements ActionListener {

	private DionaRap_Hauptfenster fenster;
	private DialogSettings dSettings;
	MTConfiguration conf;
	
	public ListenerSettingsDialog(DionaRap_Hauptfenster _fenster, DialogSettings _dSettings) {
		fenster = _fenster;
		dSettings = _dSettings;
		conf= fenster.getConf();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String Befehl= e.getActionCommand();

		dSettings.dispose();
		fenster.getController().setMultiThreaded(conf);
		
		if ("OK".equals(Befehl)) {			
			System.out.println("OK");
			dSettings.performNeuEinstellungen();
			fenster.spielStart();
		}

	}

}
