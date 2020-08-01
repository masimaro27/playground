package sort;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class sort_example_feat_grouping {

	public static void main(String[] args) {
		String[] data = {"ac", "fr", "cro", "fro", "frod", "frodo", "fc", "fb", "fa", "f"};
		
//		Arrays.sort(data, new Comparator<String>() { 
//			@Override public int compare(String o1, String o2) { 
//				return Integer.compare(o1.length(), o2.length()); 
//			} 
//		});

		/**
		 * 문자열 길이 정렬
		 */
		Arrays.sort(data, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
		
		/**
		 * 알파벳 정렬
		 */
		Map<Integer, List<String>> grouping = Arrays.stream(data).collect(Collectors.groupingBy(String::length));
		Iterator<Integer> iter = grouping.keySet().iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			grouping.put(key, grouping.get(key).stream().sorted().collect(Collectors.toList()));
		}
		
		/**
		 * 문자열 검색
		 */

		
		String test = "????o";
		String test2 = "fro??";
		String[] s = test.split("");
		Arrays.stream(s).forEach(System.out::println);
		int wordCount = test.length() - (test.lastIndexOf("?") + 1);
		int wildcardCount = test.length() - wordCount;
		
		System.out.println(test.length() - wordCount);
		System.out.println(test2.lastIndexOf("fro"));
		
//		Arrays.stream(data).forEach(d -> System.out.println(d));
	}
	
}
