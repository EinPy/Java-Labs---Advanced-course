package game;

import javax.swing.JOptionPane;

public class UX {
	
	public static void print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public static String ask(String msg) {
		String ans;
		ans = JOptionPane.showInputDialog(null, msg);
		return ans;
	}

}
