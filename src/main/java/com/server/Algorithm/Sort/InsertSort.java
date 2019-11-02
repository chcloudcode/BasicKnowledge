package com.server.Algorithm.Sort;

/**
 * 插入排序 时间复杂度：O(n^2) 适合小规模数据的排序
 * 
 * @author chenh
 *
 */

public class InsertSort extends BaseSort {

	/**
	 * 从小到大顺序
	 * 
	 * @param a
	 */
	public static void sort(int a[]) {
		for (int i = 1; i < a.length; i++) {
			// 从第二个元素开始，同前面元素作比较，放在合适位置。直到遍历完前面元素。以此类推。
			// i 前面的数是有序的，有序区；后面为无序区。
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				swap(a, j, j - 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 11, 19, 16, 14, 10, 13, 15, 18, 17, 12 };
		if (!isSorted(a)) {
			sort(a);
		}
		show(a);
	}

}
