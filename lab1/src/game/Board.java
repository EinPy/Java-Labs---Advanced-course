package game;

public class Board {
	private int noPins;
	
	public void setUp(int noPins) {
		this.noPins = noPins;
	}
	
	public void takePins(int t) {
		noPins -= t;
	}
	
	public int getNoPins() {
		return noPins;
	}
}
