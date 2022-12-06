package mountain;
import java.util.Arrays;
import java.util.HashMap;

import fractal.*;

public class Mountain extends Fractal {
	private Point a, b, c;
	private int deviation;
	private HashMap<Side, Point> mids;
	

	public Mountain(Point a, Point b, Point c, int deviation) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.deviation = deviation;
		this.mids = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
    @Override
	public String getTitle() {
		return "Einar's mountain";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
    @Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(a.getX(), a.getY());
		
		FractalTriangle(turtle, a, b, c, order, deviation);
	}

	private void FractalTriangle(TurtleGraphics turtle, Point a2, Point b2, Point c2, int order, int dev) {
		if (order == 0) {
			//move from a to b to c
			turtle.penDown();
			turtle.moveTo(a2.getX(),a2.getY());
			turtle.forwardTo(b2.getX(),b2.getY());
			turtle.forwardTo(c2.getX(), c2.getY());
			turtle.forwardTo(a2.getX(), a2.getY());

		}else {
			//dont really need to draw anything but a new triangle in the middle of previous triangle
			//however, still needs to split into 4 to get 4 new recursive calls
			double r1 = RandomUtilities.randFunc(dev);
			double r2 = RandomUtilities.randFunc(dev);
			double r3 = RandomUtilities.randFunc(dev);
			
			Side ab = new Side(a2, b2);
			Side bc = new Side(b2, c2);
			Side ac = new Side(a2, c2);
			
			Point mid1, mid2, mid3;
			if (mids.containsKey(ab)) {
				mid1 = mids.get(ab);
			}else {
				mid1 =  new Point(a2.getX() + (b2.getX() - a2.getX())/2,(int) (a2.getY() + (b2.getY() - a2.getY())/2 + r1));
				mids.put(ab, mid1);
			}
			if (mids.containsKey(ac)) {
				mid2 = mids.get(ac);
			}else {
				mid2 = new  Point(a2.getX() + (c2.getX() - a2.getX())/2, (int) (a2.getY() + (c2.getY() - a2.getY())/2 + r2));
				mids.put(ac, mid2);
			}
			if (mids.containsKey(bc)) {
				mid3 = mids.get(bc);
			}else {
				mid3 = new  Point(b2.getX() + (c2.getX() - b2.getX())/2, (int)  (b2.getY() + (c2.getY() - b2.getY())/2 + r3));
				mids.put(bc, mid3);
			}
			

			FractalTriangle(turtle, a2, mid1, mid2, order - 1, dev / 2);
			FractalTriangle(turtle, mid1, b2 ,mid3, order - 1, dev /2 );
			FractalTriangle(turtle, mid1, mid2, mid3, order - 1, dev / 2);
			FractalTriangle(turtle, mid2, mid3, c2, order - 1, dev / 2);
		}
	}

}
