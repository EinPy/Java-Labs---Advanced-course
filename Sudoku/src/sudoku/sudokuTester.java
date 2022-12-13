package sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


class sudokuTester {
	public SudokuSolver grid;

	@BeforeEach
	void setUp() throws Exception {
		this.grid = new BacktrackingSolver();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.grid.clear();
	}

	@Test
	void testEmpty() {
		assertEquals(true, grid.solve(), "Solving empty grid should be true");
	}
	@Test
	void duplicateRow() {
		grid.add(0, 0, 5);
		grid.add(0, 1, 5);
		assertEquals(false, grid.solve(), "Unsolvable case");
	}
	
	@Test
	void duplicateColumn() {
		grid.add(0, 0, 5);
		grid.add(0, 7, 5);
		assertEquals(false, grid.solve(), "Unsolvable case");
	}
	
	@Test
	void sameArea() {
		grid.add(0, 0, 5);
		grid.add(1, 1, 5);
		assertEquals(false, grid.solve(), "Unsolvable case, two elements in same area");
	}
	
	@Test
	void unsolvableBoard() {
		int[][] board = {
				  { 1, 2, 3, 0, 0, 0, 0, 0, 0 },
				  { 4, 5, 6, 0, 0, 0, 0, 0, 0 },
				  { 0, 7, 0, 7, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 3, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
				};
		grid.setMatrix(board);
		assertEquals(false, grid.solve(), "unsolvable case");
		grid.remove(2, 3);
		assertEquals(true, grid.solve(), "should be solvable after removing 7");
		
	}
	
	@Test
	void Clear() {
		grid.add(0, 0, 5);
		grid.add(0, 1, 5);
		assertEquals(false, grid.solve(), "Unsolvable case");
		grid.clear();
		assertEquals(true, grid.solve(), "solving empty grid");
		
	}
	@Test
	void badNumberInput() {
		assertThrows(IllegalArgumentException.class, () -> {
			grid.add(0, 0, 0);
		});
	}
	@Test
	void badIndexInput() {
		assertThrows(IllegalArgumentException.class, () -> {
			grid.add(-1, 0, 0);
		});
	}

	@Test
	void testRemove() {
		grid.add(0, 0, 1);
		grid.remove(0, 0);
		assertEquals(0, grid.get(0, 0), "Should be removed");
	}
	
	@Test
	void testGet() {
		grid.add(0, 0, 1);
		assertEquals(1, grid.get(0, 0), "Should be retrievable");
	}
	@Test
	void testIsValid() {
		grid.add(0, 0, 1);

		assertEquals(false, grid.isValid(0,1,1), "Should be invalid");
	}
	
	@Test
	void testsetMaxtrx() {
		int[][] board = {
				  { 1, 2, 3, 0, 0, 0, 0, 0, 0 },
				  { 4, 5, 6, 0, 0, 0, 0, 0, 0 },
				  { 0, 7, 0, 7, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 3, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
				};
		grid.setMatrix(board);
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				assertEquals(board[r][c], grid.get(r, c), "should have become board");
			}
		}
	}
	
	@Test
	void testGetMaxtrx() {
		int[][] board = {
				  { 1, 2, 3, 0, 0, 0, 0, 0, 0 },
				  { 4, 5, 6, 0, 0, 0, 0, 0, 0 },
				  { 0, 7, 0, 7, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 3, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
				};
		grid.setMatrix(board);
		assertEquals(grid.getMatrix(), board, "should be the same");
		}
	

}
