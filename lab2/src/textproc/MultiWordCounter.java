package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor{
	private Map<String, Integer> lCount;

	public MultiWordCounter(String[] landskap) {
		lCount = new TreeMap<String, Integer>();
		for (String w: landskap) {
			lCount.put(w, 0);
		}
	}
	public void process(String w) {
		//fancy way:
		//lCount.computeIfPresent(w, (k,v) -> v + 1);
		if (lCount.containsKey(w)) {
			// HashMap not mutable? Have to overwrite?
			lCount.put(w,lCount.get(w) + 1);
		}
		
	}


	public void report() {
		for (String s : lCount.keySet()) {
			System.out.println(s + ": " + lCount.get(s));
		}
	}

}
