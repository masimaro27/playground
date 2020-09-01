package basic.stack_queue;

import java.util.stream.IntStream;

public class Top {

	public static void main(String[] args) {
		int[] height = {3, 9, 9, 3, 5, 7, 2};
		Top top = new Top();
		int[] answer = top.solution(height);
		IntStream.of(answer).forEach(i -> System.out.print(i+ ","));
		
	}
	
	private int[] solution(int[] height) {
		
		int i,j;
		int[] answer = new int[height.length];
		
		for(i=height.length-1; i>=0; i--) {
			if (i == 0) {
				answer[i] = 0;
				break;
			}
			
			for (j=i-1; j>=0; j--) {
				if (height[i] < height[j]) {
					answer[i] = j + 1;
					break;
				}
				if (j == 0) {
					answer[i] = 0;
				}
			}
		}
		
		return answer;
	}
	
	
	
}
