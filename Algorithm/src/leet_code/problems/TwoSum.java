package leet_code.problems;

import java.util.stream.IntStream;
/**
 * 
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 * 
 * @author minhyuk
 *
 */
public class TwoSum {

	public static void main(String[] args) {
		
		TwoSum ts = new TwoSum();
		int[] answer = ts.twoSumByBruteForce(new int[]{2, 7, 11, 15}, 9);
		IntStream.of(answer).forEach(System.out::println);
		
		
	}
	
	public int[] twoSumByHashTable(int[] nums, int target) {
		return null;
	}
	
	public int[] twoSumByBruteForce(int[] nums, int target) {
		int i,j;
		int[] answer = new int[2];
		for (i=0; i < nums.length - 1; i++) {
			for (j=i+1; j<nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					answer[0] = i;
					answer[1] = j;
					return answer;
				}
			}
		}
		
		return answer;
	}
	
}
