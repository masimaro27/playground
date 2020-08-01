package basic;

import java.util.Arrays;

public class shift {
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		int[] data = {3,5,6,7,10};
		int i;
		
		int temp = data[data.length - 1];
		for (i= data.length-1; i>=1; i--) {
			data[i] = data[i-1];
		}
		data[0] = temp;
		
		System.out.println(Arrays.toString(data));
		
		
		for (int r=0; r < data.length; r++) {
			temp = data[0];
			for(i=0; i < data.length - 1; i++) {
				data[i] = data[i+1];
			}
			data[data.length - 1] = temp;
			System.out.println(Arrays.toString(data));
		}
	}
}
