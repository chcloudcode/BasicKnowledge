package com.server.LeetCode.simple;

public class ClimbStairs {

//    public int climbStairs(int n) {
//        if(n == 1) return 1;
//        if(n == 2) return 2;
//        return climbStairs(n-1)+climbStairs(n-2);
//    }

    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int i=3,f1 = 1,f2 = 2,sum = 0;
        while(i<=n){
            sum=f1+f2;
            f1=f2;
            f2=sum;
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        int result = c.climbStairs(4);
        System.out.println(result);
    }

}
