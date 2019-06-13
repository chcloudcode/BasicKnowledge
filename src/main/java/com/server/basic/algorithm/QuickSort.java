package com.server.basic.algorithm;

/**
 * 快速排序
 */


public class QuickSort extends BaseSort{

	public static void sort(int[] a, int start, int end) {
		if (start < end) {
			int mid = OnceQuickSort(a, start, end);
			sort(a, start, mid - 1);
			sort(a, mid + 1, end);
		}
	}

	public static int OnceQuickSort(int[] a, int start, int end) {
		int i = start;
		int j = end;
		int temp = i;
		while (i < j) {
			if (temp == i) {
				if (a[i] > a[j]) {
					swap(a, i, j);
					temp = j;
					i++;
				} else {
					j--;
				}
			} else {
				if (a[i] > a[j]) {
					swap(a, i, j);
					temp = i;
					j--;
				} else {
					i++;
				}
			}
			if (temp == i && i == start) {
				i++;
				j = end;
			}
			if (temp == j && j == end) {
				j--;
				i = start;
			}
		}
		System.out.println("进行一次快排后：");
		show(a);
		System.out.println();
		return i;
	}

	public static void main(String[] args) {

		int[] a = new int[] { 7, 6, 8, 5, 1, 3, 2, 9, 4, 0 };
		System.out.println("before sort:");
	    show(a);
		sort(a, 0, a.length - 1);
		System.out.println("after sort:");
		show(a);

	}

}