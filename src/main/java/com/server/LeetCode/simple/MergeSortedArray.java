package com.server.LeetCode.simple;

import java.util.Arrays;

public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        int p =0,q=0,index=0;

        while(index<m+n&&m!=0&&n!=0){
            if(p==m){
                result[index++]=nums2[q];
                q++;
                continue;
            }
            if(q==n){
                result[index++]=nums1[p];
                p++;
                continue;
            }
            if(nums1[p]>=nums2[q] ){
                result[index++]=nums2[q];
                q++;
                continue;
            }
            if(nums1[p]<nums2[q]){
                result[index++]=nums1[p];
                p++;
                continue;
            }
        }
        if(n==0) result = nums1;
        if(m==0) result = nums2;
       for(int i = 0;i<m+n;i++){
           nums1[i]=result[i];
       }
        Arrays.stream(nums1).forEach(each ->{
            System.out.println(each);
        });
    }

    public static void main(String[] args) {
//        int[] arr1 = new int[]{3,9,12,15,19,25,0};
//        int[] arr2 = new int[]{1,8,17,18,30};
//        merge(arr1,6,arr2,5);

//        int[] arr1 = new int[]{2,0};
//        int[] arr2 = new int[]{1};
//        merge(arr1,1,arr2,1);

//        int[] arr1 = new int[]{1,2,3,0,0,0};
//        int[] arr2 = new int[]{2,5,6};
//        merge(arr1,3,arr2,3);

        int[] arr1 = new int[]{1};
        int[] arr2 = new int[]{};
        merge(arr1,1,arr2,0);

    }
}
