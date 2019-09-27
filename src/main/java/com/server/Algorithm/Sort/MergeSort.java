package com.server.Algorithm.Sort;

/**
 * 归并排序
 * 
 * @author chenh
 *
 */

public class MergeSort extends BaseSort {

	public static void main(String[] args) {
		int[] a = new int[] { 10, 20, 45, 98, 17, 30, 56, 70, 89, 66 };
		System.out.println("Before sort:");
		show(a);
		sort(a, 0, a.length - 1);

		System.out.println("After sort:");
		show(a);

	}

	public static void sort(int[] a, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = (start + end) / 2;
		sort(a, start, mid);
		sort(a, mid + 1, end);
		merge(a, start, mid, end);
	}

	public static void merge(int[] a, int start, int mid, int end) {
		int[] temp = new int[a.length];
		int i = start;// 左序列指针
		int j = mid + 1;// 右序列指针
		int t = 0;// 临时数组指针
		while (i <= mid && j <= end) {
			if (a[i] <= a[j]) {
				temp[t++] = a[i++];
			} else {
				temp[t++] = a[j++];
			}
		}
		while (i <= mid) {// 将左边剩余元素填充进temp中
			temp[t++] = a[i++];
		}
		while (j <= end) {// 将右序列剩余元素填充进temp中
			temp[t++] = a[j++];
		}
		t = 0;
		// 将temp中的元素全部拷贝到原数组中
		while (start <= end) {
			a[start++] = temp[t++];
		}
	}

}