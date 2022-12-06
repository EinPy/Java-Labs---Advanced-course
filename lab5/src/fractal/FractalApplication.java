package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		Point p1 = new Point(100, 600);
		Point p2 = new Point(600, 200);
		Point p3= new Point(1100, 800);
		
		fractals[0] = new Mountain(p1, p2, p3, 50);
	    new FractalView(fractals, "Fraktaler", 1200, 1100);
	}

}
