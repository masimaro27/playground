package programmers.level_3;

import java.util.*;

public class 이중우선순위큐 {
    private static final String ADD = "I";
    private static final String DELETE = "D";
    private static final String DELETE_MAX = "1";
    private static final String DELETE_MIN = "-1";


    LinkedList<Integer> list = new LinkedList<>();
//    PriorityQueue<Integer> queue_asc = new PriorityQueue<>();
//    PriorityQueue<Integer> queue_desc = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) {
        이중우선순위큐 q = new 이중우선순위큐();
        String[] operation = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        int[] result = q.solution(operation);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public int[] solution(String[] operations) {
        for (int i = 0; i < operations.length ; i++) {
            String operation = operations[i];
            execute(operation);
        }
        int[] answer = new int[2];

        if (list.isEmpty()) {
            return answer;
        }
        int max = list.pollFirst();
        int min = list.pollLast();
        if (max < min) {
            return answer;
        }
        answer[0] = max;
        answer[1] = min;

        return answer;
    }

    public void execute(String operation) {
        String[] split = operation.split(" ");
        String command = split[0];

        if (이중우선순위큐.DELETE.equals(command)) {
            String second_command = split[1];
            if (이중우선순위큐.DELETE_MAX.equals(second_command)) {
                list.pollFirst();
            } else {
                list.pollLast();
            }
        } else {
            list.add(Integer.parseInt(split[1]));
            list.sort(Collections.reverseOrder());
        }
    }

}
