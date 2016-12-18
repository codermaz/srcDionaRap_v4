 

import java.awt.Color;
import java.io.File;
import java.util.HashMap;

import de.fhwgt.dionarap.model.objects.Ammo;

public class Settings {


	public static int LABEL_DIMENSION = 50 ; // in pixel, quadratisch
	public static int BUTTONS_GROESSE = 80; // Set die Groesse of JButtons in pixels im Navigationsfenster
	public static int ENTFERNUNG_ZUM_SPIELBRETT = 20; // Die Entfernung zwichen Navigationsfenster und Spielbrett in pixels bei X Acshe
	public static Color BRETT_COLOR1 = Color.white;
	public static Color BRETT_COLOR2= Color.black;
	public static String THEME="alien";
	
	public static int MUNITION_ANZAHL_ZUBEGIN = 3;	//drm.setShootAmount(Settings.MUNITION_ANZAHL_ZUBEGIN);
	public static int MUNITION_ANZAHL_FUREINAMMO = 1; //drm.setAmmoValue(Settings.MUNITION_ANZAHL_FUREINAMMO);	
	public static int MUNITION_ANZAHL_AUFDEMFELD = 5; // drm.addAmmo(new Ammo());
	// for (int i = 0; i < Settings.MUNITION_ANZAHL_AUFDEMFELD; i++) drm.addAmmo(new Ammo());
	
	public static int TOOLBAR_PANEL_ANZAHL=5;
	public static int TOOLBAR_HEIGHT=50;
	
	public static final String oStartWT = "OpponentStartWaitTime"; //= 3000 > 5 Sekunden am Anfang Schlaf
	public static final String oWaitT = "OpponentWaitTime"; //=2000 Gegner warten vor jedem Zug 2 Sekunden
	public static final String sWaitT = "ShotWaitTime"; //=500  ein Schuss ben�tigt eine halbe Sekunde

	public static final String rOppWT = "RandomOpponentWaitTime"; //=false   keine zuf�llige Wartezeit
	public static final String aColWObs = "AvoidCollisionWithObstacles"; // = true;
	public static final String aColWOpp = "AvoidCollisionWithOpponent"; //=false;
	public static final String sGetsOT = "ShotGetsOwnThread"; //=true 	nicht unbegrenzte Anzahl Sch�sse

	public static final String aAlgSA = "AlgorithmAStarActive"; //= true;
	public static final String mTime = "MinimumTime"; //=800 > 0,8 Sekunden
	public static final String dOppWT = "DynamicOpponentWaitTime"; //=false  immer gleichlang warten

	public static final String zeilenA = "ZeilenAnzahl";
	public static final String spaltenA = "SpaltenAnzahl";
	public static final String gegnerA = "GegnerAnzahl";
	public static final String hindernisA = "HindernisAnzahl";
	public static final String levelS = "level";
	public int levelNo;
	private boolean customLevel;
	
	public HashMap<String, String> einstellungen;
	
	private boolean soundOn;
	String[] files = { "Beamer.wav", "Error1.wav", "GameOver.wav", "Gewonnen.wav", "Bewegung.wav",
			"TreffenHindernis.wav", "Waffe_LASER12-MULTI-BURSTS.wav" };
	String fs = File.separator;
	String fileDir = System.getProperty("user.dir") + fs + "sounds" + fs ;


	Settings(HashMap<String, String> _einstellungen, int _levelNo, boolean _customLevel) {
		levelNo= _levelNo;
		customLevel = _customLevel;
		einstellungen=_einstellungen;
		if (einstellungen.isEmpty()) {
			setMapMTKonfig(levelNo);
		} else {
			if (!customLevel) 
				setMapMTKonfig(levelNo);
		}
		
	}

	public File getFile(int index) {
		return ( new File(fileDir + files[index]) );
	}

	public void setMapMTKonfig(int level) {
		switch (level) {
		case 0:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "false");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			
			einstellungen.put(oStartWT, "3000");
			einstellungen.put(oWaitT, "2000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "false");
			
			einstellungen.put(spaltenA, "10");
			einstellungen.put(zeilenA, "10");
			einstellungen.put(gegnerA, "5");
			einstellungen.put(hindernisA, "5");
			einstellungen.put(levelS, "0");
			break;
		case 1:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "2000");
			einstellungen.put(oWaitT, "1500");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "10");
			einstellungen.put(zeilenA, "10");
			einstellungen.put(gegnerA, "5");
			einstellungen.put(hindernisA, "5");
			einstellungen.put(levelS, "1");
			break;
		case 2:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "2000");
			einstellungen.put(oWaitT, "1000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "true");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "10");
			einstellungen.put(zeilenA, "10");
			einstellungen.put(gegnerA, "5");
			einstellungen.put(hindernisA, "5");
			einstellungen.put(levelS, "2");
			break;
		case 3:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "false");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			
			einstellungen.put(oStartWT, "3000");
			einstellungen.put(oWaitT, "2000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "false");
			
			einstellungen.put(spaltenA, "13");
			einstellungen.put(zeilenA, "13");
			einstellungen.put(gegnerA, "10");
			einstellungen.put(hindernisA, "10");
			einstellungen.put(levelS, "3");
			break;
		case 4:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "2000");
			einstellungen.put(oWaitT, "1500");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "13");
			einstellungen.put(zeilenA, "13");
			einstellungen.put(gegnerA, "10");
			einstellungen.put(hindernisA, "10");
			einstellungen.put(levelS, "4");
			break;
		case 5:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "1000");
			einstellungen.put(oWaitT, "2000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "true");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "13");
			einstellungen.put(zeilenA, "13");
			einstellungen.put(gegnerA, "10");
			einstellungen.put(hindernisA, "10");
			einstellungen.put(levelS, "5");
			break;
		case 6:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "false");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			
			einstellungen.put(oStartWT, "3000");
			einstellungen.put(oWaitT, "2000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "false");
			
			einstellungen.put(spaltenA, "20");
			einstellungen.put(zeilenA, "15");
			einstellungen.put(gegnerA, "15");
			einstellungen.put(hindernisA, "15");
			einstellungen.put(levelS, "6");
			break;
		case 7:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "2000");
			einstellungen.put(oWaitT, "1500");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "false");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "20");
			einstellungen.put(zeilenA, "15");
			einstellungen.put(gegnerA, "15");
			einstellungen.put(hindernisA, "15");
			einstellungen.put(levelS, "7");
			break;
		case 8:
			einstellungen.put(aAlgSA, "true");
			einstellungen.put(aColWObs, "true");
			einstellungen.put(aColWOpp, "true");
			einstellungen.put(mTime, "800");
			einstellungen.put(sGetsOT, "true");
			einstellungen.put(oStartWT, "1000");
			einstellungen.put(oWaitT, "2000");
			einstellungen.put(sWaitT, "500");
			einstellungen.put(rOppWT, "true");
			einstellungen.put(dOppWT, "true");
			
			einstellungen.put(spaltenA, "20");
			einstellungen.put(zeilenA, "15");
			einstellungen.put(gegnerA, "15");
			einstellungen.put(hindernisA, "15");
			einstellungen.put(levelS, "8");
			break;
		}

	}
	
	public  HashMap<String, String> getEinstellungen() {
		return einstellungen;
	}
	
	public void setEinstellungen(HashMap<String, String> einstellungen) {
		this.einstellungen = einstellungen;
	}


	public int getLevel() {
		return Integer.parseInt(levelS);
	}
	
	
	public void setLevel(int level) {
		einstellungen.put(levelS, Integer.toString(level));
	}

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}
	
}
