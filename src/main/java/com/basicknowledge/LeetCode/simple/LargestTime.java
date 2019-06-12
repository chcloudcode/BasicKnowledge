package com.basicknowledge.LeetCode.simple;

/**
 *Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 * Example 1:
 * Input: [1,2,3,4]
 * Output: "23:41"
 *
 * Example 2:
 * Input: [5,5,5,5]
 * Output: ""
 *
 * Note:
 * A.length == 4
 * 0 <= A[i] <= 9
 */

public class LargestTime {

    public String largestTimeFromDigits(int[] A) {
        int result = -1;
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A.length; j++) {
                if (i == j) continue;
                int hour = A[i] * 10 + A[j];
                if (hour > 23) continue;
                for (int m = 0; m < A.length; m++) {
                    if (m == i || m == j) continue;
                    for (int n = 0; n < A.length; n++) {
                        if (n == i || n == j || n == m) {
                            continue;
                        }
                        int minut = A[m] * 10 + A[n];
                        if (minut > 59) continue;
                        result = Math.max(result, hour * 100 + minut);
                    }
                }
            }
        return result == -1 ? "": (result/100 < 10 ? "0" + result/100: result/100) + ":" + (result % 100 < 10 ? "0" + result%100: result%100);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3};
        LargestTime l = new LargestTime();
        System.out.println(l.largestTimeFromDigits(arr));
    }
}

