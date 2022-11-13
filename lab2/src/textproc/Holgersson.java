package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor nCount = new SingleWordCounter("norge");
		MultiWordCounter multiCount = new MultiWordCounter(REGIONS);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		//Non counted words
		Scanner s1 = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (s1.hasNext()) {
			String w = s1.next().toLowerCase();
			stopwords.add(w);
		}
		GeneralWordCounter gCount = new GeneralWordCounter(stopwords);
		
		

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			p.process(word);
			nCount.process(word);
			multiCount.process(word);
			gCount.process(word);
		}

		s.close();

		p.report();
		nCount.report();
		multiCount.report();
		System.out.println("All words: ");
		gCount.report();
		
		long t1 = System.nanoTime();
		System.out.println((t1-t0)/1000000.0);
		
	}
}