package basic;

public class next_permutation {

	
	public int[] nextPermutation(int[] nums) {
		int[] copies = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			copies[i] = nums[i];
		}
		
		int idx = -1;
		
		for (int i = 0; i < copies.length - 1; i++) {
			if (copies[i] < copies[i + 1]) {
				idx = i;
			}
		}
		
		if (idx < 0) {
			return null;
		}
		
		for (int i = copies.length -1; i > idx; i--) {
			if (copies[idx] < copies[i]) {
				int tmp = copies[idx];
				copies[idx] = copies[i];
				copies[i] = tmp;
				break;
			}
		}
		
		for (int i = idx + 1; i < (copies.length + idx + 1) / 2; i++) {
			int tmp = copies[i];
			copies[i] = copies[copies.length - (i - idx)];
			copies[copies.length - (i - idx)] = tmp;
		}
		
		return copies;
		
	
	}
}


