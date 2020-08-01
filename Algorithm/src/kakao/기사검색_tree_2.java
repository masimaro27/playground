package kakao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class 기사검색_tree_2 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] answer = s.solution();
		IntStream.of(answer).forEach(System.out::println);
	}
}

class Node {
	Character c;
	int count;
	Map<Character, Node> children;

	public Node(Character c) {
		this.c = c;
		count = 0;
		children = new HashMap<>();
	}
}

class Tree {
	Node head;
	int totalCount;
	
	public Tree() {
		head = new Node(null);
		totalCount = 0;
	}
	
	public void insert(String word) {
		Node currentNode = head;
		
		char[] chars = word.toCharArray();
		for(Character c: chars) {
			if (!currentNode.children.keySet().contains(c)) {
				currentNode.children.put(c, new Node(c));
			}
			currentNode = currentNode.children.get(c);
			currentNode.count += 1;
		}
		this.totalCount += 1;
	}

	public int startWith(String query) {
		Node currentNode = head;
		char[] chars = query.toCharArray();
		for(Character c: chars) {
			if ('?' == c) {
				break;
			}
			if (!currentNode.children.containsKey(c)) {
				return 0;
			} else
				currentNode = currentNode.children.get(c);
		}
		return currentNode.count;
	}
}

class Solution {
	 public int[] solution() {
		String[] words = {"frodo", "front", "frost", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "k?", "k??", "k???", "k????", "k?????", "?", };
		int[] answer = new int[queries.length];
		/**
		 * 길이별 tree 생성
		 */
		Set<Integer> wordLen = new HashSet<>();
		Map<Integer, Tree> nTree = new HashMap<>();
		Map<Integer, Tree> rTree = new HashMap<>();
		StringBuilder builder = new StringBuilder();
		
		for (String word: words) {
			wordLen.add(word.length());
		}
		
		for (int len: wordLen) {
			nTree.put(len, new Tree());
			rTree.put(len, new Tree());
		}
		
		
		for (String word: words) {
			nTree.get(word.length()).insert(word);
			rTree.get(word.length()).insert(builder.append(word).reverse().toString());
			builder.setLength(0); // Stringbuilder 초기화
		}
		
		/**
		 * find answer
		 */
		for(int i = 0; i < queries.length; i++) {
			int count = 0;
			if (wordLen.contains(queries[i].length())) {
				if (queries[i].matches("\\?+")) {
					count = nTree.get(queries[i].length()).totalCount;
				} else if (queries[i].charAt(0) == '?') {
					builder.setLength(0); // Stringbuilder 초기화
					count = rTree.get(queries[i].length()).startWith(builder.append(queries[i]).reverse().toString());
				} else {
					count = nTree.get(queries[i].length()).startWith(queries[i]);
				}
			} 
			answer[i] = count;
		}
		return answer;
	 }
}