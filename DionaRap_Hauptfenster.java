  

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.*;



public class DionaRap_Hauptfenster extends JFrame {

	/** 
	 *  
	 */
	private static final long serialVersionUID = 1L;
	SpielSteuern spielSteuern;
	public boolean keyListenerAktiviert=false;
	
	
	public DionaRap_Hauptfenster () {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DionaRap");
		setResizable(false);
		
		addWindowFocusListener();
		spielStart();
		
		this.setVisible(true);
		this.pack();
		this.requestFocus();
	}

	private void addWindowFocusListener() {
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				DionaRap_Hauptfenster.this.requestFocus();
			}
			@Override
			public void windowGainedFocus(WindowEvent e) {}
		});
	}
	
	public void spielStart() {
	
		spielSteuern = new SpielSteuern(this);
		Navigator navisFenster = new Navigator(this);
		this.addComponentListener(new ListenerSpielBrett(navisFenster));
		
		this.getSpielSteuern().getSpielfeld().setzeAllePawns();
	}
	
	public DionaRapModel getDrm () {
		return spielSteuern.getDrm();
	}

	public DionaRapController getController() {
		return spielSteuern.getController();
	}
	
	public SpielSteuern getSpielSteuern() {
		return spielSteuern;
	}


	public static void main(String[] args) {
		new DionaRap_Hauptfenster ();
	
	}
 
}
