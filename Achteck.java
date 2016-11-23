import java.awt.Polygon;

//Die Klasse Polygon implementiert das Interface Shape
public class Achteck extends Polygon {

	// a : Länge der Kante
	Achteck(int a) {
		int b = (int) (a / Math.sqrt(2)); 

		this.addPoint(b+8, 0);
		this.addPoint((b + a)-8, 0);
		this.addPoint((2 * b + a), b+8);
		this.addPoint((2 * b + a), (b + a)-8);
		this.addPoint((b + a)-8, (2 * b + a));
		this.addPoint(b+8, (2 * b + a));
		this.addPoint(0, (b + a)-8);
		this.addPoint(0, b+8);
		
	}

}