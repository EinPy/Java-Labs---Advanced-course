package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {
	
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader",600,400));
	}

	private Object createWindow(GeneralWordCounter counter, String title, int w, int h) {
		JFrame f = new JFrame(title);
		f.setPreferredSize(new Dimension(w,h));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container p = f.getContentPane(); //container for elementes like buttons
		
		SortedListModel listModel = new SortedListModel(counter.getWordList());
		JList L = new JList(listModel);
		JScrollPane sP = new JScrollPane(L);
		
		p.add(sP, BorderLayout.NORTH);
		
		//Create bottom row
		JPanel panel = new JPanel();
		
		JButton alph = new JButton("Alphabetical");
		alph.addActionListener(event -> {
			System.out.println("test");
		});
		
		//Sorting alphabertically
		Comparator<Map.Entry<String, Integer>> comparatorAlph 
		= (a, b) -> a.getKey().compareTo(b.getKey());
		
		alph.addActionListener(event -> {
			listModel.sort(comparatorAlph);
		});
		
		Comparator<Map.Entry<String, Integer>> comparatorFreq
		= (a,b) -> b.getValue() - a.getValue();
		
		JButton freq = new JButton("Frequency");
		
		freq.addActionListener(event -> {
			listModel.sort(comparatorFreq);
		});
		
		panel.add(alph);
		panel.add(freq);
		
		p.add(panel, BorderLayout.SOUTH);
		
		JPanel inp = new JPanel();
		JTextField field = new JTextField(20);
		JButton findWord = new JButton("Find");

		f.getRootPane().setDefaultButton(findWord);

		findWord.addActionListener(event ->{
			for (int i  = 0; i < listModel.getSize();i++) {
//				System.out.println(((Entry<String, Integer>) listModel.getElementAt(i)).getKey());
//				System.out.println(field.getText());
				if (((Entry<String, Integer>) listModel.getElementAt(i)).getKey().toLowerCase().equals(field.getText().trim().toLowerCase())) {
					L.ensureIndexIsVisible(i);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "The word you are searching for is not in the database", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
			return;
		});
		
		inp.add(field);
		inp.add(findWord);
		
		
		p.add(inp, BorderLayout.EAST);
		
		
		f.pack();
		f.setVisible(true);
		
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
