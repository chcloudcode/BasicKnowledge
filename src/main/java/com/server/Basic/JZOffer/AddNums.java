package com.server.Basic.JZOffer;

/**
 * 给定一个非负num , 反复将 各个位置上的数字相加，直到得到的结果位数为1
 *
 * 例如：输入38
 * 过程：3+8 = 11
 *           1 + 1 = 2
 * 输出：2
 *
 * 进阶：不适用循环和递归 复杂度为O(1)
 */
public class AddNums {

    public static int addNums(int source){
        if(source<0) return 0;
        if(source>0&&source<10) return source;

        while(source>=10){
            int len = String.valueOf(source).length();
            int result  = 0;
            int target = source;
            for(int i = len-1;i>0;i--){
              int t = (int) Math.pow(10,i);
              result = result +  target / t;
              target = target%t;
            }
            result+=target;
            if(result>=10) source=result;
            else return result;
        }
        return 0;

    }

    public static void main(String[] args) {
        System.out.println(addNums(123456));
    }
}


// 进阶答案：
// abc - (a+b+c) = 100a + 10b +c -a -b-c = 99a+9b = 9 *n
// 例如：358
// 358 - （3+5+8）=9 * n
//  16 - (1 + 6) = 9 *n
// 累加：358-7= 9 * n
//即：358= 9*n + 7      358%9 = 7  结果为 7


// 这种 编程题 建议 能够实现即可，找规律的部分了解就行。
