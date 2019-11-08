package com.server.Algorithm.Sort;

/**
 * 快速排序
 */


public class QuickSort extends BaseSort{

	public static void sort(int[] arr,int p,int r){
		if(p>=r) return;
		int q = partition(arr,p,r);
		sort(arr,p,q-1);
		sort(arr,q+1,r);
	}

	private static int partition(int[] arr, int p, int r) {
		int pivot = arr[r];
		int i = p;
		for (int j = p; j < r; j++) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
			}
		}

		swap(arr, i, r);
		System.out.print("找到中间点：i = "+i+"  ");
		System.out.print("此时结果为：");
		show(arr);
		return i;
	}

	public static void main(String[] args) {

		int[] a = new int[] { 7, 6, 8, 5, 1, 3, 2, 9, 4, 0 };
	    show(a);
		sort(a, 0, a.length - 1);
		show(a);

	}

}