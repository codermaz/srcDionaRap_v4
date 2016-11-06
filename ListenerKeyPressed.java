import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerKeyPressed implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP: case 73: // 73=i
			System.out.println("oben");
			break;
		case KeyEvent.VK_DOWN: case 78: // 78=n
			System.out.println("unten");
			break;
		case KeyEvent.VK_LEFT: case 72: // 72=h 
			System.out.println("links");
			break;
		case KeyEvent.VK_RIGHT: case 75: // 75=k
			System.out.println("rechts");
			break;
		 case 85: // 85=u 
			System.out.println("links_oben");
			break;
		case 66: // 66=b
			System.out.println("links_down");
			break;
		 case 79: // 79=o 
			System.out.println("rechts_oben");
			break;
		 case 77: // 77=m
			System.out.println("rechts_down");
			break;	
		 case 32: case 74: // 32=space  und 74=j
			System.out.println("shiessen***");
			break;	
		}
		
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}
