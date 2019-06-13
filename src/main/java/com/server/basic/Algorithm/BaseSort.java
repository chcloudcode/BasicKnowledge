package com.server.basic.Algorithm;

public class BaseSort {
	
	/**
	 * 交换数组元素值
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	/**
	 * 显示数组元素
	 * 
	 * @param a
	 */
	public static void show(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	/**
	 * 判断是否已经有序
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > a[i + 1])
				return false;
		}
		return true;
	}

}
