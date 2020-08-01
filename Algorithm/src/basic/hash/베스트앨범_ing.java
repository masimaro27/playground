package basic.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ���� ����
��Ʈ���� ����Ʈ���� �帣 ���� ���� ���� ����� �뷡�� �� ���� ��� ����Ʈ �ٹ��� ����Ϸ� �մϴ�. �뷡�� ���� ��ȣ�� �����ϸ�, �뷡�� �����ϴ� ������ ������ �����ϴ�.

���� �뷡�� ���� ����� �帣�� ���� �����մϴ�.
�帣 ������ ���� ����� �뷡�� ���� �����մϴ�.
�帣 ������ ��� Ƚ���� ���� �뷡 �߿����� ����
 ��ȣ�� ���� �뷡�� ���� �����մϴ�.
�뷡�� �帣�� ��Ÿ���� ���ڿ� �迭 genres�� �뷡�� ��� Ƚ���� ��Ÿ���� ���� �迭 plays�� �־��� ��, ����Ʈ �ٹ��� �� �뷡�� ���� ��ȣ�� ������� return �ϵ��� solution �Լ��� �ϼ��ϼ���.

���ѻ���
genres[i]�� ������ȣ�� i�� �뷡�� �帣�Դϴ�.
plays[i]�� ������ȣ�� i�� �뷡�� ����� Ƚ���Դϴ�.
genres�� plays�� ���̴� ������, �̴� 1 �̻� 10,000 �����Դϴ�.
�帣 ������ 100�� �̸��Դϴ�.
�帣�� ���� ���� �ϳ����, �ϳ��� � �����մϴ�.
��� �帣�� ����� Ƚ���� �ٸ��ϴ�.
����� ��
genres	plays	return
[classic, pop, classic, classic, pop]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
����� �� ����
classic �帣�� 1,450ȸ ����Ǿ�����, classic �뷡�� ������ �����ϴ�.

���� ��ȣ 3: 800ȸ ���
���� ��ȣ 0: 500ȸ ���
���� ��ȣ 2: 150ȸ ���
pop �帣�� 3,100ȸ ����Ǿ�����, pop �뷡�� ������ �����ϴ�.

���� ��ȣ 4: 2,500ȸ ���
���� ��ȣ 1: 600ȸ ���
���� pop �帣�� [4, 1]�� �뷡�� ����, classic �帣�� [3, 0]�� �뷡�� �״����� �����մϴ�.

�� ���� - 2019�� 2�� 28�� �׽�Ʈ���̽��� �߰��Ǿ����ϴ�.
 * @author PC-006
 *
 */

public class ����Ʈ�ٹ�_ing {
	public static void main(String[] args) {
		
		String[] genres = {"classic", "pop", "classic", "classic", "pop", "balad"};
		int[] plays = {500, 600, 501, 800, 900, 2000};
		
		int[] answer = ����Ʈ�ٹ�_ing.solution(genres, plays);
		for (int i: answer) {
			System.out.println(i);
		}
	}
	
	public static int[] solution(String[] genres, int[] plays) {
		int i;
		
		Map<String, Integer> pGenres = new HashMap<>();
		Map<Integer, Integer> pPlays = new HashMap<>();
		
		// [���� �뷡�� ���� ����� �帣] 
		for (i = 0; i < genres.length; i++) {
			if (pGenres.containsKey(genres[i])) {
				pGenres.replace(genres[i], pGenres.get(genres[i]) + plays[i]);
				continue;
			} 
			pGenres.put(genres[i], plays[i]);
		}
		
		List<Entry<String, Integer>> pgEntries = new ArrayList<>(pGenres.entrySet());
		Collections.sort(pgEntries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		
		for (Entry<String, Integer> entry: pgEntries) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		// [���� �뷡�� ���� ����� �帣]
		
		
		List<Integer> bestAlbum = new ArrayList<>();
		// [�帣 ������ ���� ���� ����� �뷡]
		Iterator<Entry<String, Integer>> iter = pgEntries.iterator();
		while(iter.hasNext()) {
			if (!pPlays.isEmpty())
				pPlays.clear();
			Entry<String, Integer> key = iter.next();
			for (i = 0 ; i < genres.length; i++) {
				if (key.getKey().equals(genres[i])) {
					pPlays.put(i, plays[i]);
				}
			}
			//[���Ƚ���� �����ϸ� ������ȣ�� ������ �켱]
			
			List<Entry<Integer, Integer>> ppEntries = new ArrayList<>(pPlays.entrySet());
			Collections.sort(ppEntries, (o1, o2) -> {
				if (o2.getValue() > o1.getValue()) {
					return 1;
				}
				if (o2.getValue().equals(o1.getValue())) {
					if (o2.getKey() < o1.getKey()) {
						return 1;
					} else {
						return -1;
					}
				}
				return -1;
			});
			//[���Ƚ���� �����ϸ� ������ȣ�� ������ �켱]
			
			//[�� �帣 �� �ִ� 2��]
			for (i = 0 ; i < ppEntries.size(); i++) {
				bestAlbum.add(ppEntries.get(i).getKey());
				if (i == 1) {
					break;
				}
			}
			//[�� �帣 �� �ִ� 2��]
		}
		// [�帣 ������ ���� ���� ����� �뷡]
		
		return bestAlbum.stream().mapToInt(o -> o).toArray();
	}
}

