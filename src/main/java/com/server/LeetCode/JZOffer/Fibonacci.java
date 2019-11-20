package com.server.LeetCode.JZOffer;



public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(Fibonacci(6));
		System.out.println(Fibonacci2(6));
	}

	// ??????
	public static int Fibonacci(int n) {
		if (n < 1) {
			return 0;
		} else if (n >= 1 && n <= 2) {
			return 1;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	// ???????
	public static int Fibonacci2(int n) {
		if (n < 1) {
			return 0;
		} else if (n >= 1 && n <= 2) {
			return 1;
		} else {
			int[] a = new int[n];
			a[0] = 1;
			a[1] = 1;
			for (int j = 3; j <= n; j++) {
				a[j - 1] = a[j - 2] + a[j - 3];
			}
			return a[n - 1];
		}
	}

}
