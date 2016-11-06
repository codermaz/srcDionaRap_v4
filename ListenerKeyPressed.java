import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerKeyPressed implements KeyListener {

	public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

        System.out.println(e.getKeyChar() + " typed");
    }

}
