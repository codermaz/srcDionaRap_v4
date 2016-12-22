public class FragenUndNotizen { FragenUndNotizen () {

/*
 * 
 5.4 Levels 
 5.5 Sounds 
 5.6 Bonus  
  
 *
> jnlp : Whelche Host und wie hochladen?

> validate , repaint, updateUI
	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
			}
		});
> _variable namen

> @SuppressWarnings("unchecked") in DialogSettings
	neuEinstellungen = ((HashMap<String, String>) fenster.getSettings().getEinstellungen().clone());
> UTF8 in Eclipse Editor

> static : wann?
> methode rufen aus anderen methoden  
> objekt orientiert
> generic 

> MVC : Folders
> Eclipse : Strg + Alt + D : Notizen
> Microsoft Visual Studio
> Free Audio Files

##########
> MVC
> JavaDoc
> ColorChooser
> Datum
> File I/O
> Graphics 2D
> EditorPane
> SplitPane / TabbedPane


> JDBC
> JavaFX
> Java Web Start

********** 
Threads:

1. runnable klasse : public class RunnableBlinkingMunition implements Runnable {
	in runnable
	1.1. <<in konstruktor (sender bekommen)>>
			tbm = _tbm;
	1.2. <<in run methode>>
		1.1.1. (in sender legende) methode aufrufen mit "sleep()"
		   	tbm.blinkingMunition(tbm.PR_FARBE_red,3);
			try {
				Thread.sleep(blinkingTime);
			} catch (InterruptedException e) {
				tbm.blinkingMunition(tbm.PR_FARBE_blau,1);
			}
2. <<in sender>> 
	2.1. <<runnable variable deklarieren>>
			private RunnableBlinkingMunition rbm;
	2.2. <<thread variable deklarieren>>
			private Thread threadBlinking = null;
	2.3. <<runnable Instanz>>
	 		rbm = new RunnableBlinkingMunition(this);
	2.4. <<Thread erzeugen mit runnable Instanz>> 
		 	threadBlinking = new Thread(rbm);

3. in sender
	3.1. <<in start methode>>
	  3.1.1 if (!threadBlinking.isAlive()) {
			threadBlinking = new Thread(rbm);
			threadBlinking.start();
			}
	3.2. <<in stop methode>>
	  3.2.1 if (threadBlinking.isAlive()) 
			threadBlinking.interrupt();
4. <<in anderer Klasse>>
	4.1. start methode aufrufen
			if (fenster.getDrm().getShootAmount() == 0) // munitionAnzahl
				fenster.getToolBarMenu().startBlinking();




*/

	
}	
}
