 
    
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JWindow;

public class ListenerNavigator implements ComponentListener {
	private JWindow fenster;

	public ListenerNavigator(JWindow fenster) {
		this.fenster = fenster;
	}

	@Override
	public void componentResized(ComponentEvent event) {
		JFrame gui = (JFrame) event.getSource();
		fenster.setLocation(
				(int) gui.getLocation().getX() + gui.getWidth() + Settings.ENTFERNUNG_ZUM_SPIELBRETT,
				(int) gui.getLocation().getY());
	}

	@Override
	public void componentMoved(ComponentEvent event) {
		JFrame gui = (JFrame) event.getSource();
		fenster.setLocation(
				(int) gui.getLocation().getX() + gui.getWidth() + Settings.ENTFERNUNG_ZUM_SPIELBRETT,
				(int) gui.getLocation().getY());
		fenster.repaint();
	}

	@Override
	public void componentShown(ComponentEvent e) { }

	@Override
	public void componentHidden(ComponentEvent e) {	}

}
