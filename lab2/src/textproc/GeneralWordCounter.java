package textproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
	Set<String> s;
	Map<String,Integer> wCount;

	public GeneralWordCounter(Set<String> s) {
		this.s = s;
		wCount = new TreeMap<String, Integer>();
	}

	public void process(String w) {
		if (!s.contains(w)) {
			if(wCount.containsKey(w)) {
				wCount.put(w,wCount.get(w) + 1);
			}else {
				wCount.put(w, 1);
			}
		}
	}


	public void report() {
//		for (String s : wCount.keySet()) {
//			if (wCount.get(s) >= 200) {
//				System.out.println(s + ": " + wCount.get(s));
//			}
//		}
		Set<Map.Entry<String, Integer>> wordSet = wCount.entrySet();
		//System.out.println(wordSet);
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		//System.out.println(wordList);
		wordList.sort((a, b) -> {
			if (a.getValue() != b.getValue()) {
				return   b.getValue()-a.getValue();
			}else {
				return a.getKey().compareToIgnoreCase(b.getKey());
			}
		}); // reverse sort
		//wordList.sort((a, b) -> a.getValue() - b.getValue() ); // reverse sort
		for (int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
		}
	}
	 
	

}
