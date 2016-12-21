
public class RunnableHertzAddition implements Runnable {

	private Spielfeld feld;
	private int blinkingTime = 600;
	private volatile boolean running=true;

	RunnableHertzAddition (Spielfeld _feld) {
		feld = _feld;
	}
	
	public void terminate() {
		running=false;
	}
	
	@Override
	public void run() {
		while (running) {

			if (Thread.interrupted()) {
				running=false;
				break;
			}

			feld.blinkingBonus("heart2.png");

			try {
				Thread.sleep(blinkingTime);
			} catch (InterruptedException e) {
				System.out.println("heart2"+e.toString());
				//feld.deleteHertz();
				return;
			}  

			feld.blinkingBonus("heart.png");

			try {
				Thread.sleep(blinkingTime);
			} catch (InterruptedException e) {
				System.out.println("heart1 "+e.toString());
				///feld.deleteHertz();
				return;
			}

		}

		
	}

}
