package mountain;

public class Side {
	private Point a;
	private Point b;
	
	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	public Point getA() {
		return a;
	}
	public Point getB() {
		return b;
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			if (a.equals(((Side) obj).getA()) && b.equals(((Side) obj).getB())) {
				return true;
			}else if (a.equals(((Side) obj).getB()) && b.equals(((Side) obj).getA())) { // somehow inverted
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
