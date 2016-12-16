
public class RunnableBlinkingMunition implements Runnable {

	private ToolBarMenu tbm;
	private int blinkingTime = 200;

	RunnableBlinkingMunition(ToolBarMenu _tbm) {
		tbm = _tbm;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {

			if (Thread.interrupted()) {
				tbm.blinkingMunition(tbm.PR_FARBE_blau,1);
				break;
			}

			tbm.blinkingMunition(tbm.PR_FARBE_red,3);

			try {
				Thread.sleep(blinkingTime);
			} catch (InterruptedException e) {
				tbm.blinkingMunition(tbm.PR_FARBE_blau,1);
			}

			tbm.blinkingMunition(tbm.PR_FARBE_blau,1);

			try {
				Thread.sleep(blinkingTime);
			} catch (InterruptedException e) {
				tbm.blinkingMunition(tbm.PR_FARBE_blau,1);
			}

		}
	}

}
