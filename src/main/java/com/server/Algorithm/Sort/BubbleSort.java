package com.server.Algorithm.Sort;


/**
 * 冒泡排序 时间复杂度：O(n^2)，适合小规模数据的排序
 */
public class BubbleSort extends BaseSort {

    public static void sort(int[] a){
        for(int i = 0; i<a.length-1;i++){
            for(int j = 0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                }
            }
        }
    }


    public static void main(String[] args) {
            int[] a = new int[]{2,7,0,1,6,4,8,3,9,5};
            show(a);
            sort(a);
            show(a);
    }



}
