package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class combination_ing {

	
	public static void main(String[] args) {
		int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
//            back_tracking(arr, visited, 0, n, i);
            combByRecursive(arr, n, i);
            count = 0;
        }
		
	}
	
	
	public static void combByRecursive(int[] arr, int n, int r) {
		combByRecursive(0, new Stack<Integer>(), arr, n, r);
	}
	
	public static Stack<Integer> combByRecursive(int start, Stack<Integer> visited, int[] arr, int n, int r) {
		if (visited.size() == r) {
			System.out.println(visited);
			return visited;
		}
		for (int i = start; i < n ; i++) {
			System.out.println("i = " + i);
			visited.push(arr[i]);
			visited = combByRecursive(start + 1, visited, arr, n, r);
			visited.pop();
		}
		
		return visited;
	}
	
	
	/**
	 * solve by back tracking
	 * @param arr
	 * @param visited
	 * @param start
	 * @param n
	 * @param r
	 */
	public static int count = 0;
	public static void back_tracking(int[] arr, boolean[] visited, int start, int n, int r) {
		count ++;
		System.out.println("count = " + count);
		if (r == 0) {
			print(arr, visited, n);
			return;
		}
		
		
		for (int i = start; i<n; i++) {
			String log= String.format("i = %d, n = %d, before visited = %s, visited = %s, ", i, n, visited[i-1 < 0 ? 0 : i-1], visited[i]);
			System.out.println(log);
			visited[i] = true;
			back_tracking(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}
	
	static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
	
	
	
}
