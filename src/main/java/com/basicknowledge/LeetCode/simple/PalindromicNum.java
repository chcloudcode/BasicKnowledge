package com.basicknowledge.LeetCode.simple;

/**
 *  回文数
 *  判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 *  示例 1:
 *  输入: 121
 *  输出: true
 *
 *  示例 2:
 *  输入: -121
 *  输出: false
 *  *
 *  示例 3:
 *  输入: 10
 * 输出: false
 *
 */

public class PalindromicNum {

    public boolean isPalindrome(int x) {
        char[] arr = String.valueOf(x).toCharArray();
        if(arr[0]=='-') return false;
        int start = 0;
        int end = arr.length-1;
        while (start<end){
            if(arr[start]==arr[end]){
                start++;
                end--;
                continue;
            }else{
                return false;
            }
        }
        if(start>=end) return true;
        return false;
    }


    public static void main(String[] args) {
            PalindromicNum p = new PalindromicNum();
        System.out.println(p.isPalindrome(11));
    }

}
