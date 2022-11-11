package game;

import java.util.Random;

public class ComputerPlayer extends Player {
	private Random r;

	
	public ComputerPlayer(String name) {
		super(name);
		r = new Random();
	}
	
	public int takePins(Board b) {
		int take;
		if (b.getNoPins() > 1) {
			take = r.nextInt(2) + 1;
		}else {
			take = 1;
		}
		b.takePins(take);
		return take;
	}
	
}
