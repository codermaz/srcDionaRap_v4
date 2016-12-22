
public class Bonus {

	private int zeile;
	private int spalte;
	private String text;
	
	Bonus(){}
	
	Bonus (DionaRap_Hauptfenster _fenster, int _zeile, int _spalte) {
		zeile=_zeile;
		spalte=_spalte;
	}
	
	public int getZeile() {
		return zeile;
	}
	
	public void setZeile(int zeile) {
		this.zeile = zeile;
	}

	public int getSpalte() {
		return spalte;
	}

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}

	public void setText(String _text) {
		text= _text;
	}
	public String getText() {
		return text;
	}
	
	
}
