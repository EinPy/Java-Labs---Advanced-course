package game;


public class HumanPlayer extends Player {
	private String line;
	private boolean asking;
	
	HumanPlayer(String name){
		super(name);
	}
	public int takePins(Board b) {
		asking = true;
		while (asking) {
			try {
				line = UX.ask("How many pins do you want to take?");
				if (line == null) {
					return -2;
				}
				int take = Integer.parseInt(line);
				if (take <0 || take >2) {
					UX.print("Follow the rules of the game idiot");
				}else if (take > b.getNoPins()) {
					UX.print("You can't take pins that not exist idiot");
				}else {
					b.takePins(take);
					return take;
				}
			}catch (Exception e) {
				UX.print("That is not a number you fuckign dingus, try again");
			}
		}
		return -1; //something broke
	}

}
