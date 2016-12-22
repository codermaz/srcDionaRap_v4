import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerKeyPressed implements KeyListener {

	DionaRap_Hauptfenster fenster;
	Move move;

	ListenerKeyPressed(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		move = new Move(fenster);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// System.out.println("keyCode :"+ e.getKeyCode());
		switch (keyCode) {
		case KeyEvent.VK_UP:
		case 73:
		case 104:
		case 224: // 73=i Contoller.dir= 8
			// System.out.println("oben");
			move.richtung(8);
			break;
		case KeyEvent.VK_DOWN:
		case 78:
		case 98:
		case 225: // 78=n
			// System.out.println("unten");
			move.richtung(2);
			break;
		case KeyEvent.VK_LEFT:
		case 72:
		case 100:
		case 226: // 72=h
			// System.out.println("links");
			move.richtung(4);
			break;
		case KeyEvent.VK_RIGHT:
		case 75:
		case 102:
		case 227: // 75=k
			// System.out.println("rechts");
			move.richtung(6);
			break;
		case 85:
		case 36:
		case 103: // 85=u
			// System.out.println("links_oben");
			move.richtung(7);
			break;
		case 66:
		case 35:
		case 97: // 66=b
			// System.out.println("links_unten");
			move.richtung(1);
			break;
		case 79:
		case 33:
		case 105: // 79=o
			// System.out.println("rechts_oben");
			move.richtung(9);
			break;
		case 77:
		case 34:
		case 99: // 77=m
			// System.out.println("rechts_unten");
			move.richtung(3);
			break;
		case 32: // 32=space
		case 74: // 74=J
		case 101: // 74=j
		case 65368:
			move.schiess();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}
