import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerMouse implements MouseListener {

	private DionaRap_Hauptfenster fenster;
	private JLabel[][] labelFelder;
	int mouseZ; // Zeile von Mouse
	int mouseS; // Spalte von Mouse
	int playerZ; // Zeile von Player
	int playerS; // Spalte von Player
	int difZ; // Zeilendifferenz
	int difS; // Spaltendifferenz
	private Move move;
	private Bonus bonus;

	ListenerMouse(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		move = new Move(fenster);

	}

	public void setLabelFelder(JLabel[][] _labelFelder) {
		labelFelder = _labelFelder;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void playerMove(int _art) {
		if (_art == 1) { // if mouse in Umgebung
			if ((Math.abs(difS) < 2) && (Math.abs(difZ) < 2)) {
				if ((difS == -1) && (difZ == 1))
					move.act(1);
				if ((difS == 0) && (difZ == 1))
					move.act(2);
				if ((difS == 1) && (difZ == 1))
					move.act(3);
				if ((difS == -1) && (difZ == 0))
					move.act(4);
				if ((difS == 1) && (difZ == 0))
					move.act(6);
				if ((difS == -1) && (difZ == -1))
					move.act(7);
				if ((difS == 0) && (difZ == -1))
					move.act(8);
				if ((difS == 1) && (difZ == -1))
					move.act(9);
				if ((difS == 0) && (difZ == 0)) {
					if (fenster.getDrm().getShootAmount() == 0) { // munitionAnzahl
						if (fenster.isSoundOn())
							fenster.getSettings().getSoundError().play();
						fenster.getToolBarMenu().startBlinking();
					} else {
						if (fenster.isSoundOn())
							fenster.getSettings().getSoundShoot().play();
						move.schiess();
					}
				}
			}
		} else { // MouseClick muss nicht in der Umgebung sein
			if ((difS < 0) && (difZ > 0))
				move.act(1);
			if ((difS == 0) && (difZ > 0))
				move.act(2);
			if ((difS > 0) && (difZ > 0))
				move.act(3);
			if ((difS < 0) && (difZ == 0))
				move.act(4);
			if ((difS > 0) && (difZ == 0))
				move.act(6);
			if ((difS < 0) && (difZ < 0))
				move.act(7);
			if ((difS == 0) && (difZ < 0))
				move.act(8);
			if ((difS > 0) && (difZ < 0))
				move.act(9);
			if ((difS == 0) && (difZ == 0)) {
				move.schiess();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			int zeilenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.zeilenA));
			int spaltenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA));
			// get Mouse Koordination
			for (int i = 0; i < zeilenA; i++) { // fuer Zeilen
				for (int j = 0; j < spaltenA; j++) { // fuer Spalten
					if (labelFelder[j][i].equals(e.getSource())) {
						mouseZ = i;
						mouseS = j;
					}
				}
			}
			playerS = fenster.getDrm().getPlayer().getX();
			playerZ = fenster.getDrm().getPlayer().getY();

			difS = mouseS - playerS;
			difZ = mouseZ - playerZ;

			playerMove(2); // 2--> es ist nicht noetig, in der Umgebung von
							// Player zu sein
			
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			PopupMenu menu = new PopupMenu(fenster);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	private boolean bonusIcon(int _mouseZ, int _mouseS) {
		if (bonus.getZeile() == _mouseZ || bonus.getSpalte() == _mouseS)
			return true;
		else
			return false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
