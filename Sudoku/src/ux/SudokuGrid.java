package ux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.text.Position;

import sudoku.BacktrackingSolver;
import sudoku.SudokuSolver;
public class SudokuGrid extends JPanel {

	private static final Font FONT = new Font("Verdana", Font.BOLD, 20);


	private static final int EMPTY = 0; // empty cell
	private static final int SIZE = 9;


	//Frame Components
	private JFrame frame;
	private JTextField[][] field_arr;
	private JButton jbutton;
	private JButton jbuttonC;

	public SudokuSolver grid;

	/**
	 * Initilalize program, setup board and add buttons and event listners. 
	 */
	public SudokuGrid(){
		this.field_arr = new JTextField[SIZE][SIZE];
		this.grid = new BacktrackingSolver();
		setupUX();
	}

	private void setupUX() {

		Color[] colors = {Color.white,Color.ORANGE};
		frame = new JFrame("SUDOKU SOLVER");  //Creating frame

		for (int row = 0; row < SIZE; row ++) {
			for (int col = 0; col < SIZE; col++) {
				field_arr[row][col] = new JTextField();
				field_arr[row][col].setBounds(50  + col * 40, 50 + row * 40, 30, 30); // position

				// Color scheme from task
				if (row >= 3 && row <= 5) {
					if (col >= 3 && col <= 5) {
						field_arr[row][col].setBackground(colors[1]); // orange
					}else {
						field_arr[row][col].setBackground(colors[0]); // white
					}
				}else {
					if (col >= 3 && col <= 5) {
						field_arr[row][col].setBackground(colors[0]); // white
					}else {
						field_arr[row][col].setBackground(colors[1]); // orange
					}
				}
				field_arr[row][col].setFont(FONT);
				field_arr[row][col].setHorizontalAlignment(SwingConstants.CENTER);
				
				field_arr[row][col].addKeyListener(new KeyListener(){
					@Override
					public void keyReleased(KeyEvent e) {
						//not very effective works
						if (!updateModel()) {
							JOptionPane.showMessageDialog(null, "That is not a valid input, do you know how sudoku works", "Invalid input", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					@Override
					public void keyTyped(KeyEvent e) {
						
					}
					@Override
					public void keyPressed(KeyEvent e) {
						
					}
				});
				
				frame.add(field_arr[row][col]);
			}
		}
		//Creating and adding the Solve JButton
		jbutton = new JButton("SOLVE");                  
		jbutton.setBounds(100,450,80,50);
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				//Start method present in background_code.java
				if (solve()) {
					// we are happy
				}else {
					//not happy
					JOptionPane.showMessageDialog(null, "The sudoku has no solution", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.add(jbutton);
		
		//Creating and adding the clear JButton
		jbuttonC = new JButton("CLEAR");                  
		jbuttonC.setBounds(300,450,80,50);
		jbuttonC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				//Start method present in background_code.java
				clear();
				
			}
		});
		frame.add(jbuttonC);

		//Centering the frame and set size
		center();
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void center() {
		//get size of current screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width/2 -frame.getWidth()/2 ,dimension.height/2 - frame.getHeight()/2);
		frame.setSize(500, 600);
	}
	
	private int getElement(int row, int col) {
		String txt = field_arr[row][col].getText();
		if(txt.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(txt.trim());
	}
	
	private void setVal(int row, int col, int val) {
		field_arr[row][col].setText(String.valueOf(val));
	}
	private void clear() {
		grid.clear();
		updateUX();
	}
	private boolean solve() {
		boolean ans = grid.solve();
		if (ans) {
			updateUX();
		}
		return ans;
	}
	
	private void updateUX() {
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				if (grid.get(r, c) == 0) {
					field_arr[r][c].setText("");
				}else {
					field_arr[r][c].setText(Integer.toString(grid.get(r, c)));
				}
			}
		}
	}
	
	private boolean updateModel() {
		for (int r = 0; r <= 8; r++) {
			for (int c = 0; c <= 8; c++) {
				String s = field_arr[r][c].getText();
				try {
					if (s.equals("")) {
						grid.remove(r,c);
					}else {
						int num = Integer.parseInt(s);
						grid.add(r, c, num);
					}
				}catch (Exception e) {
					grid.remove(r, c);
					field_arr[r][c].setText("");
					return false;
				}
			}
		}
		return true;
	}
}
