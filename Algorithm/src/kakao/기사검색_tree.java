package kakao;
import java.util.*;
public class 기사검색_tree {
	public static void main(String[] args) {
		Solution_x s = new Solution_x();
		String[] words = {"fdo", "ab", "f", "frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "?????"};
		s.solution(words, queries);
	}
}
class Section {
    int left, right;
    int length;
    public Section(int left, int right, int length) {
        this.left = left;
        this.right = right;
        this.length = length;
    }

    @Override
    public String toString() {
        return "left:" + left + " right:" + right + " length:" + length;
    }
}

class Solution_x {

    List<Section> sections = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    Comparator<String> comp = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    };

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        makeSection(words);
        count(words, queries, answer);
        return answer;
    }

    private void makeSection(String[] words) {
    	// 정렬
        Arrays.sort(words, comp);   
        int len = -1;
        int start = 0;
        int i;
        
        for(i = 0; i < words.length; i++) {
            if(i == 0) {
                len = words[i].length();
                continue;
            }
            if(len != words[i].length()) {
                sections.add(new Section(start, i-1, len));
                len = words[i].length();
                start = i;
            }
        }
        sections.add(new Section(start, i-1, len)); 
    }

    private void count(String[] words, String[] queries, int[] answer) {
        for(int i = 0; i < queries.length; i++) {
            String query = queries[i];

            if(map.containsKey(query)) {
                answer[i] = map.get(query);
                continue;
            }

            String keyWord = "";
            int flag = -1;
            if(query.charAt(0) == '?') {
                int startIdx = query.lastIndexOf("?") + 1;
                keyWord = query.substring(startIdx);
                flag = 0;
            }
            else {
                int endIdx = query.indexOf("?");
                keyWord = query.substring(0, endIdx);
                flag = 1;
            }
            int cnt = 0;
            int idx = findSection(query);
            if(idx == -1)
                answer[i] = cnt;
            else {
                Section section = sections.get(idx);
                if(flag == 0) { //쿼리가 ?로 시작
                    for(int j = section.left; j <= section.right; j++) {
                        if(words[j].endsWith(keyWord)) 
                            cnt++;
                    }
                }
                else { //쿼리가 문자로 시작
                    for(int j = section.left; j <= section.right; j++) {
                        if(words[j].startsWith(keyWord))
                            cnt++;
                    }
                }
                answer[i] = cnt;
            }

            map.put(query, cnt);
        }
    }

    private int findSection(String word) {
        int left = 0, right = sections.size() - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            int midLength = sections.get(mid).length;
            if(word.length() == midLength) {
                return mid;
            }
            else if(word.length() < midLength) {
                right = mid -1;
            }
            else
                left = mid + 1;
        }
        return -1;
    }
}
