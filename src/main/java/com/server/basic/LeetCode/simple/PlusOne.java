package com.server.basic.LeetCode.simple;

/**
 * 加一
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 *
 */

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int current = digits.length-1;
        while (digits[current]+1>9){
            digits[current]=0;
            current--;
            if(current<0){
                int[] res = new int[digits.length+1];
                res[0]=1;
                for(int i = 1;i<res.length;i++){
                    res[i]=digits[i-1];
                }
                return res;
            }
        }
        digits[current]+=1;
        return digits;
    }

    public static void main(String[] args) {
//         int[] digits = new int[]{9,8,2,1,3,3,1,8,1,4,4,7,2,7,2,0,5,6,8,9,7,7,4,3};
        int[] digits = new int[]{9,8,9};
        PlusOne p = new PlusOne();
        int[] res = p.plusOne(digits);
        for(int i = 0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
    }
}
