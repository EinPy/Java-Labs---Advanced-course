package sudoku;

import java.util.HashSet;
import java.util.Set;

public class BacktrackingSolver implements SudokuSolver{
	private int[][] Board;
	public static final int EMPTY = 0; // empty cell
	public static final int SIZE = 9; // size of our Sudoku grids

	/**
	 * Initilializes board filled with zeroes
	 */
	public BacktrackingSolver() {
		this.Board = new int[SIZE][SIZE];
	}
	/**
	 * Does an initial check if the board is obviously unsolvable, then starts backtracking check
	 */
	public boolean solve() {
		for (int idx = 0; idx < SIZE; idx++) {
			boolean ans1, ans2;
			ans1 = valid_row(idx);
			ans2 = valid_col(idx);
			if (ans1 == false || ans2 == false) {
				return false;
			}
		}
		boolean ans3 = valid_subsquares();
		if (!ans3) {
			return false;
		}
		return solveSudoku(0,0);
	}
	
	/**
	 * 
	 * Recursively attemptes diffrent combinations to see if sudoku is solvable
	 * @param row
	 * @param col
	 * @return true if solvable, else false
	 */
	private boolean solveSudoku(int row,int col)
	{

		if (row == SIZE - 1 && col == SIZE)
			return true;

		if (col == SIZE) {
			row++;
			col = 0;
		}

		if (Board[row][col] != 0)
			return solveSudoku(row, col + 1);

		for (int num = 1; num < 10; num++) {
			
			if (isValid(row,col, num)) {

				Board[row][col] = num;


				if (solveSudoku(row, col + 1))
					return true;
			}
			/* removing the assigned num , since our
assumption was wrong , and we go for next
assumption with diff num value   */
			Board[row][col] = 0;
		}
		return false;
	}

	/**
	 * Attempt to insert digit at position
	 * @param row
	 * @param col
	 * @throws IllegalArgumentException
	 */
	public void add(int row, int col, int digit) {
		if (row >= 0 && row <= 8 && col >= 0 && col <= 8 ) {
			if (digit >= 1 && digit <= 9) {
				Board[row][col] = digit;
			}else {
				throw new IllegalArgumentException("Invalid input number outside range (1,9)");
			}
		}else {
			throw new IllegalArgumentException("Invalid input outside range (0,8)");
		}
	}


	/**
	 * Remove element at  posiition
	 * @throws IllegalArgumentException
	 */
	public void remove(int row, int col) {
		if (row >= 0 && row <= 8 && col >= 0 && col <= 8) {
			Board[row][col] = 0;
		}else {
			throw new IllegalArgumentException("Invalid position outside range (0,8)");
		}

	}

	/**
	 * @param row
	 * @param col
	 * @throws IllegalArgumentException
	 * @returns value at position
	 */
	public int get(int row, int col) {
		if (row >= 0 && row <= 8 && col >= 0 && col <= 8) {
			return Board[row][col];
		}else {
			throw new IllegalArgumentException("Invalid position outside range (0,8)");
		}
	}

	// we check if a possible number is already in a row
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (Board[row][i] == number)
				return true;

		return false;
	}

	// we check if a possible number is already in a column
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (Board[i][col] == number)
				return true;

		return false;
	}

	// we check if a possible number is in its 3x3 box
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (Board[i][j] == number)
					return true;

		return false;
	}

	/**
	 * 
	 * Check if number can be plced on position in grid
	 * @param row
	 * @param col
	 * @param number
	 * @return true if number can be placed on position row, col, else false
	 */
	public boolean isValid(int row, int col, int number) {
		return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
	}


	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	public void clear() {
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				Board[r][c] = 0;
			}
		}

	}
	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	public void setMatrix(int[][] m) {
		if (m.length == 9 && m[0].length == 9) {
			Board = m;
		}else {
			throw new IllegalArgumentException("Wrong dimensions of board");
		}

	}


	public int[][] getMatrix() {
		return Board;
	}

	/**
	 * 
	 * Returns the entire sudoku grid as a matrix
	 * @return int[][] the sudoku grid
	 */
	private boolean valid_row(int row){
		int temp[] = Board[row];
		Set<Integer>set = new HashSet<Integer>(); 
		for (int value : temp) {
			if (value != 0 && set.add(value) == false) {
				return false;
			}
		}
		return true;
	}

	private boolean valid_col(int col){
		Set<Integer>set = new HashSet<Integer>(); 
		for (int i =0 ; i< 9; i++) {
			if (Board[i][col] != 0){
				if (set.add(Board[i][col]) == false) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean valid_subsquares(){
		for (int row = 0 ; row < 9; row = row + 3) {
			for (int col = 0; col < 9; col = col + 3) {
				Set<Integer>set = new HashSet<Integer>(); 
				for(int r = row; r < row+3; r++) {
					for(int c= col; c < col+3; c++){
						if (Board[r][c] != 0){
							if (set.add(Board[r][c]) == false) {
								return false;
							}
						}
					}
				}
			}   
		}
		return true;
	}

}
