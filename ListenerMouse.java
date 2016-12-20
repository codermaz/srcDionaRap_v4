import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerMouse implements MouseListener {

	DionaRap_Hauptfenster fenster;
	JLabel[][] labelFelder;
	int mouseZ; // mouse Zeile
	int mouseS; // mouse Spalte
	int playerZ; // Zeile von Player
	int playerS; // Spalte von Player
	int difZ; // Zeilendifferenz
	int difS; // Spaltendifferenz

	ListenerMouse(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
	}

	public void setLabelFelder(JLabel[][] _labelFelder) {
		labelFelder = _labelFelder;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void playerMove (int _art) {
		if (_art==1) {  // if mouse in Umgebung
			if ((Math.abs(difS) < 2) && (Math.abs(difZ) < 2)) {
				if ((difS == -1) && (difZ == 1))
					fenster.getController().movePlayer(1);
				if ((difS == 0) && (difZ == 1))
					fenster.getController().movePlayer(2);
				if ((difS == 1) && (difZ == 1))
					fenster.getController().movePlayer(3);
				if ((difS == -1) && (difZ == 0))
					fenster.getController().movePlayer(4);
				if ((difS == 1) && (difZ == 0))
					fenster.getController().movePlayer(6);
				if ((difS == -1) && (difZ == -1))
					fenster.getController().movePlayer(7);
				if ((difS == 0) && (difZ == -1))
					fenster.getController().movePlayer(8);
				if ((difS == 1) && (difZ == -1))
					fenster.getController().movePlayer(9);
				if ((difS == 0) && (difZ == 0)) {
					DionaRapController drc = fenster.getController();
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
		} else { // muss nicht in der Umgebung sein
				if ((difS < 0) && (difZ > 0))
					fenster.getController().movePlayer(1);
				if ((difS == 0) && (difZ >0 ))
					fenster.getController().movePlayer(2);
				if ((difS >0) && (difZ >0))
					fenster.getController().movePlayer(3);
				if ((difS < 0) && (difZ == 0))
					fenster.getController().movePlayer(4);
				if ((difS > 0) && (difZ == 0))
					fenster.getController().movePlayer(6);
				if ((difS < 0) && (difZ < 0))
					fenster.getController().movePlayer(7);
				if ((difS == 0) && (difZ < 0))
					fenster.getController().movePlayer(8);
				if ((difS > 0) && (difZ < 0))
					fenster.getController().movePlayer(9);
				if ((difS == 0) && (difZ == 0)) {
					DionaRapController drc = fenster.getController();
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
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			int zeilenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.zeilenA));
			int spaltenA = Integer.parseInt(fenster.getSettings().getEinstellungen().get(Settings.spaltenA));
			// get Player Koordination
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

			playerMove (2);

		} else if (e.getButton() == MouseEvent.BUTTON3) {
			PopupMenu menu = new PopupMenu(fenster);
			menu.show(e.getComponent(), e.getX(), e.getY());
		}

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
