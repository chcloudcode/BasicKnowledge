package com.server.LeetCode.JZOffer;

import java.util.Stack;


public class TwoStackRealQueue {

	static Stack<Integer> stack1 = new Stack<Integer>();
	static Stack<Integer> stack2 = new Stack<Integer>();

	public static void push(int node) {
		stack1.push(node);
	}

	public static int pop() {
		if (stack2.empty()) {
			while (!stack1.empty()) {
				stack2.push(stack1.pop());
			}
		}
		int tmp = stack2.pop();
		System.out.println(tmp);
		return tmp;
	}

	public static void main(String[] args) {
		push(1);
		push(2);
		push(3);
		pop();
		pop();
		push(4);
		pop();
		push(5);
		pop();
		pop();
	}
}
