package com.server.Basic.JZOffer;

import java.util.ArrayList;
import java.util.Collections;




class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}

public class PrintList {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		System.out.println(printListFromTailToHead(node1));

	}

	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> result = new ArrayList<>();
		if (listNode != null) {
			while (listNode.next != null) {
				result.add(listNode.val);
				listNode = listNode.next;
			}
			result.add(listNode.val);
			Collections.reverse(result);
		}
		return result;

	}

}
