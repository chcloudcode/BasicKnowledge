package com.server.basic.JZOffer;



public class FindInTwoArray {

	public static void main(String[] args) {
		int[][] array = new int[][] {};
		System.out.println(Find(16, array));
	}

	public static boolean Find(int target, int[][] array) {

		for (int i = 0; i < array.length; i++) {
			int width = array[i].length;
			if (target < array[i][0] || target > array[i][width - 1] || width == 0) {
				continue;
			} else {
				for (int j = 0; j < width; j++) {
					if (target == array[i][j]) {
						return true;
					}
				}
			}
		}
		return false;

	}

}
