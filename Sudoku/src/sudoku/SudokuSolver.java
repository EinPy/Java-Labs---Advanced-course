package sudoku;

public interface SudokuSolver {
	
	/**
	 * 
	 * Solves sudoku recursively with backtracking
	 * @return true if board is solvable, else false
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes the element at position row, col in grid
	 * @param row
	 * @param col
	 */
	void remove(int row, int col);

	/**
	 * 
	 * @param row
	 * @param col
	 * @return  IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	int get(int row, int col);

	/**
	 * 
	 * Check if number can be plced on position in grid
	 * @param row
	 * @param col
	 * @param number
	 * @return true if number can be placed on position row, col, else false
	 */
	boolean isValid(int row, int col, int number);

	/**
	 * Removes all elements in grid
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * 
	 * Returns the entire sudoku grid as a matrix
	 * @return int[][] the sudoku grid
	 */
	int[][] getMatrix();
}