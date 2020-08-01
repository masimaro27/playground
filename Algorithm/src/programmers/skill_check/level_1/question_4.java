package programmers.skill_check.level_1;

import java.util.Arrays;
import java.util.stream.IntStream;
/**
 * �迭 array�� i��° ���ں��� j��° ���ڱ��� �ڸ��� �������� ��, k��°�� �ִ� ���� ���Ϸ� �մϴ�.

���� ��� array�� [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3�̶��

array�� 2��°���� 5��°���� �ڸ��� [5, 2, 6, 3]�Դϴ�.
1���� ���� �迭�� �����ϸ� [2, 3, 5, 6]�Դϴ�.
2���� ���� �迭�� 3��° ���ڴ� 5�Դϴ�.
�迭 array, [i, j, k]�� ���ҷ� ���� 2���� �迭 commands�� �Ű������� �־��� ��, commands�� ��� ���ҿ� ���� �ռ� ������ ������ �������� �� ���� ����� �迭�� ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.

���ѻ���
array�� ���̴� 1 �̻� 100 �����Դϴ�.
array�� �� ���Ҵ� 1 �̻� 100 �����Դϴ�.
commands�� ���̴� 1 �̻� 50 �����Դϴ�.
commands�� �� ���Ҵ� ���̰� 3�Դϴ�.
 */
public class question_4 {

	public static void main(String[] args) {
		
		question_4 q1 = new question_4();
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {
				{2, 5, 3},
				{4, 4, 1},
				{1, 7, 3}
		};
		System.out.println(Arrays.toString(q1.solution(array, commands)));
		
	}
	
	public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0 ; i < commands.length; i++) {
        		int startPos = commands[i][0];
        		int endPos = commands[i][1];
        		int findPos = commands[i][2];
        		
        		int[] slice = Arrays.copyOfRange(array, startPos - 1, endPos);
        		int[] sortedSlice = IntStream.of(slice).sorted().toArray();
        		answer[i] = sortedSlice[findPos - 1];
        }
        
        return answer;
    }
}

