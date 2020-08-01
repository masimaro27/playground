package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 가사검색 {
	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "????o"};
		
		int[] answer = new int[queries.length];
		long start = 0L;
		long end = 0L;
		
		/**
		 * 문자열 길이 정렬
		 */
		Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
		
		/**
		 * 길이별 grouping
		 */
		start = System.currentTimeMillis();
		Map<Integer, List<String>> grouping = groupingByLength_2(words);
		end = System.currentTimeMillis();
		executeTime("length sort", start, end);
		
		start = System.currentTimeMillis();
		Iterator<Integer> iter = grouping.keySet().iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			/**
			 * 알파벳 정렬
			 */
			grouping.put(key, grouping.get(key).stream().sorted().collect(Collectors.toList()));
		}
		end = System.currentTimeMillis();
		executeTime("word sort", start, end);
		
		/**
		 * 문자열 검색
		 */
		
		for(int i = 0 ; i < queries.length; i++) {
			start = System.currentTimeMillis();
			Integer queryLen = queries[i].length();
			Integer key = grouping.keySet().stream().filter(len -> {
				System.out.println("len :" + len + ", queryLen : " + queryLen + ", " + (len.equals(queryLen)));
				return len.intValue() == queryLen.intValue();
			}).findAny().orElse(-1);
//			Integer key = findKey(grouping, queryLen);
			if (queryLen == 220) {
				System.out.println("");
			}
			System.out.println("key : " + key);
			if (key == -1) {
				answer[i] = 0;
				continue;
			}
			
			String searchChar = queries[i].replaceAll("\\?", "").trim();
			int wildcardCount;
			int wordCount;
			
			
			/**
			 * 왼쪽
			 */
			if (queries[i].indexOf("?") == 0) {
				wordCount = queries[i].length() - (queries[i].lastIndexOf("?") + 1);
				int count = (int) grouping.get(key).stream().parallel().filter(word -> word.lastIndexOf(searchChar) == ((queryLen - wordCount))).count();
				answer[i] = count;
			}
			/**
			 * 오른쪽
			 */
			else {
				wildcardCount = queryLen - (queries[i].indexOf("?"));
				wordCount = queryLen - wildcardCount;
				
				int count = (int) grouping.get(key).stream().filter(word -> word.indexOf(searchChar) == 0).count();
				answer[i] = count;
			}
			end = System.currentTimeMillis();
			executeTime("search", start, end);
		}

		
		Arrays.stream(answer).forEach(System.out::println);
		
//		Arrays.stream(data).forEach(d -> System.out.println(d));
	}
	
	private static Map<Integer, List<String>> groupingByLength(String[] words) {
		 return Arrays.stream(words).collect(Collectors.groupingBy(String::length));
	}
	
	private static Map<Integer, List<String>> groupingByLength_2(String[] words) {
		  Map<Integer, List<String>> groupingData = new HashMap<Integer, List<String>>();
		  int wordLength = 0;
		  List<String> groupingWords = new ArrayList<String>();
		  
		  for (int i = 0 ; i < words.length ; i++) {
			  if (i == 0 ) {
				  wordLength = words[i].length();
				  groupingWords.add(words[i]);
				  continue;
			  }
			  
			  if (wordLength != words[i].length()) {
				  groupingData.put(wordLength, groupingWords);
				  groupingWords = new ArrayList<String>();
				  wordLength = words[i].length();
				  groupingWords.add(words[i]);
			  } else {
				  groupingWords.add(words[i]);
			  }
		  }
		  
		  groupingData.put(wordLength, groupingWords);
		  
		  return groupingData;
		  
	}
	
	private static int findKey(Map<Integer, List<String>> groupingWords, int tWordLength ) {
	       int left = 0, right = groupingWords.size() - 1;
	       Integer[] keys = (Integer[]) groupingWords.keySet().toArray(new Integer[0]);
	       while(left <= right) {
	    	   int mid = (left + right) / 2;
	    	   int midWordLength = keys[mid];
	    	   if (tWordLength == midWordLength) {
	    		   return midWordLength;
	    	   } else if (tWordLength < midWordLength) {
	    		   right = mid - 1;
	    	   } else {
	    		   left = mid + 1;
	    	   }
	    	   
	       }
	       return -1;
	       
	  }
	
	private static void executeTime(String type, long start, long end) {
		long spendTime = (end - start);
		System.out.println("["+ type +"] executeTime : " + spendTime);
	}
}
