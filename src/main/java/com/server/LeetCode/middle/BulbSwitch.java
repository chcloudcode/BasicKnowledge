package com.server.LeetCode.middle;

import java.util.Scanner;

/**
 * 灯泡开关
 * <p>
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 * <p>
 * 你应该返回 1，因为只有一个灯泡还亮着。
 */
public class BulbSwitch {

    public int bulbSwitch(int n) {
        int[][] a = new int[n][n];
        for(int i = 0 ;i < n;i++){
            a[0][i]=1;
        }
        for (int i = 1; i < n; i++) {
            for(int j = 0;j< n; j++){
                a[i][j]=a[i-1][j];
                if(i == n-1 && j== n-1){
                    a[i][n-1]= change(a[i-1][n-1]);
                }else{
                    if(j!=0 && ((j+1)%(i+1) == 0)){
                        a[i][j]=change((a[i-1][j]));
                    }
                }
            }
        }
        int count = 0;
        for(int m = 0 ;m<n;m++){
            if(a[n-1][m]==1) count++;
        }
        return count;
    }

    public int change(int a ){
        if(a == 1) return 0;
        if(a == 0) return 1;
        return a;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        BulbSwitch example = new BulbSwitch();
        System.out.println("result:"+example.bulbSwitch(n));
    }
}

