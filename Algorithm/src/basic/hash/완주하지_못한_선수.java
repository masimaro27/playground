package basic.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class 완주하지_못한_선수 {

	public static void main(String[] args) {
		String[] participant = {"leo", "marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"leo", "marina", "josipa", "nikola", "vinko"};

		String[] p2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] p3 = {"mislav", "stanko", "mislav", "ana"};
		String[] c3 = {"stanko", "ana", "mislav"};


		완주하지_못한_선수 t = new 완주하지_못한_선수();
		t.solution(participant, completion);
	}

	public String solution(String[] participant, String[] completion) {
		String answer = "";

		Map<String, Integer> map = new HashMap<>();
		for (String p: participant) {
			if (map.get(p) == null) {
				map.put(p, 1);
			} else {
				map.replace(p, map.get(p) + 1);
			}

		}

		for (String c: completion) {
			map.replace(c, map.get(c) - 1);
		}

		Entry<String, Integer> unfinished =  map.entrySet().stream().filter(nc -> nc.getValue() == 1).findFirst().orElse(null);

		System.out.println(unfinished.getKey());

		return answer;
	}

}
