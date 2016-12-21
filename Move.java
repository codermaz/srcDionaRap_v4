import de.fhwgt.dionarap.controller.DionaRapController;

public class Move {

	private DionaRap_Hauptfenster fenster;
	private DionaRapController drc;
	private Spielfeld feld;
	int playerZ; // Zeile von Player
	int playerS; // Spalte von Player
	int mouseZ; // Zeile von Mouse
	int mouseS; // Spalte von Mouse

	Move(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		drc = fenster.getController();
		feld= fenster.getSpielfeld();
	}

	public void act(int dir) {
		drc.movePlayer(dir);
		playerS = fenster.getDrm().getPlayer().getX();
		playerZ = fenster.getDrm().getPlayer().getY();
		if (playerS==feld.getBonus().getSpalte() && 
				playerZ==feld.getBonus().getZeile() ) 
			feld.bonusGewonnen();
	}

	public void schiess() {
		if (fenster.getDrm().getShootAmount() == 0) { // munitionAnzahl
			if (fenster.isSoundOn())
				fenster.getSettings().getSoundError().play();
			fenster.getToolBarMenu().startBlinking();
		} else {
			if (fenster.isSoundOn())
				fenster.getSettings().getSoundShoot().play();
			drc.shoot();
		}
	}

}
