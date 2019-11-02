package com.server.Algorithm.Sort;


/**
 * 选择排序 时间复杂度：O(n^2) 适合小规模数据的排序
 * 
 * @author chenh
 *
 */

public class SelectSort extends BaseSort {

	/**
	 * 从小到大顺序
	 * 
	 * @param a
	 */
	public static void sort(int a[]) {
		for (int i = 0; i < a.length; i++) {
			// a从第一个元素开始，同剩下元素比较，选出最小值，以此类推。
			for (int j = i+1; j < a.length; j++) {
				if (a[i] > a[j]) {
					swap(a, i, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 1, 9, 6, 4, 0, 3, 5, 8, 7, 2 };
		if (!isSorted(a)) {
			sort(a);
		}
		show(a);
	}

}
