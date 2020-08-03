package leet_code.problems.ing;
/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

 * @author minhyuk
 *
 */
public class AddTwoNumbers {

	public static boolean isCarry = false;
	
	public static void main(String[] args) {
//		ListNode l1_3 = new ListNode(3);
//		ListNode l1_4 = new ListNode(4, l1_3);
//		ListNode l1_2 = new ListNode(2, l1_4);
//		
//		ListNode l2_4 = new ListNode(4);
//		ListNode l2_6 = new ListNode(6, l2_4);
//		ListNode l2_5 = new ListNode(5, l2_6);
		
		ListNode l1_5 = new ListNode(9);
		ListNode l2_5 = new ListNode(9);
		
		AddTwoNumbers atn = new AddTwoNumbers();
		ListNode result = atn.addTwoNumbers(l1_5, l2_5);
		System.out.println("TEST");
		
	}
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			if (isCarry) {
				isCarry = false;
				return new ListNode(1);
			} else {
				return null;
			}
		}
		
		if (l1 == null) {
			l1 = new ListNode(0);
		}
		
		if (l2 == null) {
			l2 = new ListNode(0);
		}
		int sum = l1.val + l2.val;
		
		if (isCarry) {
			sum+=1;
			isCarry = false;
		}
		
		if (sum >= 10) {
			sum = sum % 10;
			isCarry = true;
		}
		
		return new ListNode(sum, addTwoNumbers(l1.next, l2.next));
    }
	
}

class ListNode {
	public int val;
	public ListNode next;
	
	ListNode() {
		
	}
	
	ListNode(int val) {
		this.val = val;
	}
	
	ListNode(int val, ListNode next) {
		this.val = val; this.next = next;
	}

	public int getVal() {
		return val;
	}

	public ListNode getNext() {
		return next;
	}
	
}
