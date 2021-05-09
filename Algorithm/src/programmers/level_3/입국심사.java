package programmers.level_3;

import java.util.*;

public class 입국심사 {
    public static void main(String[] args) {
        입국심사 q = new 입국심사();
        System.out.println(q.solution(6, new int[]{7, 10}));
    }

    public long solution(int n, int[] times) {
        long answer = 0;
        List<Long> imigration = new ArrayList<>();

        for (int i = 0; i < times.length; i++) {
            for (int j = 1; j <= n; j++) {
                imigration.add((long) (times[i] * j));
            }
        }

        imigration.sort(Comparator.naturalOrder());

        answer = imigration.get(n - 1);

        return answer;
    }

}
