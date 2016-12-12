import java.util.HashMap;

import de.fhwgt.dionarap.model.data.MTConfiguration;

public class MultiThreadKonfiguration {

	private DionaRap_Hauptfenster fenster;
	private MTConfiguration conf;
	
	MultiThreadKonfiguration (DionaRap_Hauptfenster _fenster) {
		fenster=_fenster;
		conf = new MTConfiguration();
		initKonfiguration();
	}
	
	private void initKonfiguration() {
		Settings settings = fenster.getSettings();
		HashMap<String, String> sets = settings.getEinstellungen();

		conf.setAlgorithmAStarActive(Boolean.parseBoolean(sets.get(settings.aAlgSA)));
		conf.setAvoidCollisionWithObstacles(Boolean.parseBoolean(sets.get(settings.aColWObs)));
		conf.setAvoidCollisionWithOpponent(Boolean.parseBoolean(sets.get(settings.aColWOpp)));
		conf.setMinimumTime(Integer.parseInt(sets.get(settings.mTime)));
		conf.setShotGetsOwnThread(Boolean.parseBoolean(sets.get(settings.sGetsOT))); 
		conf.setOpponentStartWaitTime(Integer.parseInt(sets.get(settings.oStartWT)));
		conf.setOpponentWaitTime(Integer.parseInt(sets.get(settings.oWaitT))); 
		conf.setShotWaitTime(Integer.parseInt(sets.get(settings.sWaitT)));
		conf.setRandomOpponentWaitTime(Boolean.parseBoolean(sets.get(settings.rOppWT))); 
		conf.setDynamicOpponentWaitTime(Boolean.parseBoolean(sets.get(settings.dOppWT))); 	
	}

	public MTConfiguration getMTKonfiguration() {
		return conf;
	}
	
}
