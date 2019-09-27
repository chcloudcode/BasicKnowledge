package com.server.basic.LeetCode.simple;

import java.util.Arrays;

public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        int i=0,target =0,index=0;
        if(m>=n) target=n;
        else target = m;
        while(i<target){
            if(nums1[i]>=nums2[i]){
                result[index]=nums1[i];
                result[++index]=nums2[i];
            }else{
                result[index]=nums2[i];
                result[++index]=nums1[i];
            }
            i++;
        }

        Arrays.stream(result).forEach(each->{
            System.out.println(each+" ");
        });
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3,9,12,15,19};
        int[] arr2 = new int[]{1,8,17,18,30};
        merge(arr1,arr1.length,arr2,arr2.length);

    }
}
