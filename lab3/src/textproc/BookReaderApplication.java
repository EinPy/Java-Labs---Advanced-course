package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner s1 = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (s1.hasNext()) {
			String w = s1.next().toLowerCase();
			stopwords.add(w);
		}
		GeneralWordCounter gCount = new GeneralWordCounter(stopwords);

		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			gCount.process(word);
		}

		s.close();

		System.out.println("All words: ");
		gCount.report();
		
		BookReaderController controller = new BookReaderController(gCount);

	}

}
