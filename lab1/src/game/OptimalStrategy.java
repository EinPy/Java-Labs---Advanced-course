package game;

public class OptimalStrategy extends Player {

	
	public OptimalStrategy(String name) {
		super(name);
	}
	
	public int takePins(Board b) {
		int take, amount;
		amount = b.getNoPins();
		//3, 6, 9
		//Optimal strategy is to always leave a multiple of 3
		//If you ever get to that point you win, if you do not start at that point
		//the only winning strategy is to play against someone dumb that
		//does not follow the optimal stratgegy. Extending the game provides 
		//more oppotunities for mistakes so in this case we always choose one
		if(amount < 3) {
			take = amount;
		}else {
			if (amount % 3 != 0) {
				take = amount % 3;
			}
			else {
				take = 1;
			}
		}
		b.takePins(take);
		return take;
	}
	
}
