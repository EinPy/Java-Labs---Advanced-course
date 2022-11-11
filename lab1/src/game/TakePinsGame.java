package game;

public class TakePinsGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board myBoard = new Board();
		myBoard.setUp(12);
		
		boolean playing = true;
		int turn = 0;
		
		Player human = new HumanPlayer("BoB");
		Player robot = new OptimalStrategy("Terminator");
		
		UX.print("test");
		
		int take;
		while (playing) {
			UX.print("The amount of pins is: " + myBoard.getNoPins());
			if (turn % 2 == 0) {
				take = human.takePins(myBoard);
				if (take == -2) {
					playing = false;
					break;
				}
				UX.print("You took: "+  take + " pins");
				if (myBoard.getNoPins() == 0) {
					UX.print("You win!");
				}
			}else {
				UX.print("robocop took: " + robot.takePins(myBoard) + " pins");
				if (myBoard.getNoPins() == 0) {
					UX.print("You loose sucker");
				}	
			}
			if (myBoard.getNoPins() == 0) {
				playing = false;
			}
			turn++;
		}
		

	}

}
