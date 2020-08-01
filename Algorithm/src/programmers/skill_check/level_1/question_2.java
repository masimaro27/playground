package programmers.skill_check.level_1;

import java.util.Arrays;

public class question_2 {

	public static void main(String[] args) {
		int[] left = {1,4,7};
		int[] right = {3,6,9};
		
		Object rightStartPos = '#';
		Object leftStartPos = '*';
		
		int[] leftPosition = getPosition(leftStartPos);
		int[] rightPosition = getPosition(rightStartPos);
		
		int currLeftPosX = leftPosition[0];
		int currLeftPosY = leftPosition[1];
		int currRightPosX = rightPosition[0];
		int currRightPosY = rightPosition[1];
		
		StringBuilder result = new StringBuilder();
		
		int[][] numbers = {
				{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
				{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}
		};
		String hand = "right";
		
		for (int i = 0 ; i < numbers.length; i ++) {
			for (int j = 0 ; j < numbers[i].length; j++) {
				int nextNumber = numbers[i][j];
				int[] pos = getPosition(nextNumber);
				if (Arrays.stream(left).anyMatch(k -> k == nextNumber)) {
					currLeftPosX = pos[0];
					currLeftPosY = pos[1];
					result.append("L");
				} else if (Arrays.stream(right).anyMatch(k -> k == nextNumber)) {
					currRightPosX = pos[0];
					currRightPosY = pos[1];
					result.append("R");
				} else {
					int lWeight = getWeight(nextNumber, currLeftPosX, currLeftPosY);
					int rWeight = getWeight(nextNumber, currRightPosX, currRightPosY);
					
					if (lWeight < rWeight) {
						result.append("L");
						currLeftPosX = pos[0];
						currLeftPosY = pos[1];
					} else if (lWeight > rWeight) {
						result.append("R");
						currRightPosX = pos[0];
						currRightPosY = pos[1];
					} else {
						if ("right".equals(hand)) {
							result.append("R");
							currRightPosX = pos[0];
							currRightPosY = pos[1];
						} else {
							result.append("L");
							currLeftPosX = pos[0];
							currLeftPosY = pos[1];
						}
					}
				}
			}
			System.out.println(result.toString());
			result = new StringBuilder();
		}
		
	}
	
	public static int getWeight(Object next, int currPosX, int currPosY) {
		int[] pos = getPosition(next);
		int x = pos[0];
		int y = pos[1];
		
		return Math.abs(currPosX - x) + Math.abs(currPosY - y); 
	}
	
	public static int[] getPosition(Object target) {
		Object[][] keypad = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				{'*',0,'#'},
		};
		int[] result = new int[2];
		
		int i, j;
		for (i = 0; i < keypad.length; i++) {
			for (j = 0; j < keypad[i].length; j++) {
				if (keypad[i][j] == target) {
					result[0] = i;
					result[1] = j;
				}
			}
		}
		
		return result;
	}
	
	
	
}

