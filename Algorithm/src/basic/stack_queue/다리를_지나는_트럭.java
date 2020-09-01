package basic.stack_queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Mark {
	private int weight;
	private int time;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public Mark(int weight, int time) {
		this.weight = weight;
		this.time = time;
	}


}

public class 다리를_지나는_트럭 {

	public static void main(String[] args) {

		int bridge_length = 100;
		int weight = 100;
		int[] truck_weight = {10,10,10,10,10,10,10,10,10,10};

		Queue<Mark> dq = new LinkedList<>();
		Queue<Integer> twq = new LinkedList<>();

		int available_weight = weight;
		int time = 0, temp = 0;

		for (int tw: truck_weight) {
			twq.offer(tw);
		}

		while(true) {
			if (dq.isEmpty() && twq.isEmpty()) {
				break;
			}
			time++;
			if (dq.peek() != null && time - dq.peek().getTime() == bridge_length) {
				available_weight += dq.poll().getWeight();
			}
			if (twq.peek() != null && available_weight >= twq.peek()) {
				temp = twq.poll();
				dq.offer(new Mark(temp, time));
				available_weight -= temp;
			}

		}

		System.out.println(time);
	}
}
