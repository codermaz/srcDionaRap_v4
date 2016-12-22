import de.fhwgt.dionarap.controller.DionaRapController;

public class Move {

	private DionaRap_Hauptfenster fenster;
	private DionaRapController drc;
	private Spielfeld feld;
	private int playerZ; // Zeile von Player
	private int playerS; // Spalte von Player

	Move(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		drc = fenster.getController();
	}

	public void richtung(int dir) {
		drc.movePlayer(dir);
		feld= fenster.getSpielfeld();
		playerS = fenster.getDrm().getPlayer().getX();
		playerZ = fenster.getDrm().getPlayer().getY();
		if (feld!=null) {
			if (playerS==feld.getBonus().getSpalte() && 
					playerZ==feld.getBonus().getZeile() ) 
				feld.bonusGewonnen();
		}
	}

	public void schiess() {
		feld= fenster.getSpielfeld();
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
