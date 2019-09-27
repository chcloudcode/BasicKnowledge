package com.server.basic.Algorithm;

/**
 * 快速排序
 */


public class QuickSort extends BaseSort{

	public static void sort(int[] a, int start, int end) {
		if (start >= end)  return ;
		int mid = OnceQuickSort(a, start, end);
		sort(a, start, mid - 1);
		sort(a, mid + 1, end);
	}

	public static int OnceQuickSort(int[] a, int start, int end) {
		int i = start;
		int j = end;
		//temp 临时 标识 中间点，最终i = j = tmp
		int temp = i;

		// 寻找中间点的位置，即左侧比中间点小，右侧比中间点大
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
			//解决 temp 一轮下来没有 移动 一直是i
			if (temp == i && i == start) {
				i++;
				j = end;
			}
			//解决 temp 一轮下来没有移动 一直是j
			if (temp == j && j == end) {
				j--;
				i = start;
			}
		}
		return temp;
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