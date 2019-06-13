package com.server.basic.LeetCode.simple;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 *
 *  示例 1:
 *  输入: 123
 *  输出: 321
 *
 *  示例 2:
 *  输入: -123
 *  输出: -321
 *
 *  示例 3:
 *  输入: 120
 *  输出: 21
 *
 *  假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 *
 */

public class ReverseInt {

    public int reverse(int x) {
        if(x==0) return 0;
        int max = (int) (Math.pow(2,31)-1);
        int min = (int) -Math.pow(2,31);
        String s = String .valueOf(x);
        Boolean flag = true;
        StringBuffer sb = null;
        long result = 0l;
        if(x<0){
            flag=false;
            sb=new StringBuffer(s.substring(1,s.length())).reverse();
        }else{
            sb=new StringBuffer(s).reverse();
        }
        if(!flag){
            result=  -Long.parseLong(sb.toString());
        }else{
            result =  Long.parseLong(sb.toString());
        }
        if(result>max||result<min){
            return 0;
        }
        return  (int) result;
    }

    public static void main(String[] args) {
        ReverseInt reverseInteger = new ReverseInt();
        System.out.println(reverseInteger.reverse(1534236469));

    }
}
