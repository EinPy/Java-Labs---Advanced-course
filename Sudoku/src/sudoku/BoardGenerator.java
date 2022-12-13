package sudoku;

import java.util.ArrayList;



public class BoardGenerator {
	private BacktrackingSolver grid;
	private ArrayList<Integer> NumberList;
	
	public BoardGenerator() {
		this.grid = new BacktrackingSolver();
		this.NumberList = new ArrayList<Integer>();
		for (int n = 1; n <= 9; n++) {
			NumberList.add(n);
		}
	}
	
	private boolean isFull() {
		int[][] test = grid.getMatrix();
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				if (test[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	private Pair<Integer> findEmpty(){
		int[][] test = grid.getMatrix();
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				if (test[r][c] == 0) {
					return new Pair<Integer>(r,c);
				}
			}
		}
		return null;
	}

	//To ensure there is only one solution, one can first generate a full valid grid, then remove
	//Random positions and check if there is still only one solution;
	
	
}
