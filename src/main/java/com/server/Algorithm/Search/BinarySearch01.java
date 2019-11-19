package com.server.Algorithm.Search;

/**
 * 二分查找变形——查找第一个等于给定值的元素
 */
public class BinarySearch01 {

    public static int search(int[] a, int target){
        int start=0,end=a.length-1;
        while(start<=end){
            int mid = start + ((end - start )>>1);
            if(a[mid]>target){
                end=mid-1;
            }else if(a[mid]<target){
                start=mid+1;
            }else{
                //因为 数组是有序的，所以当 mid不是第一个且mid == target时，
                // 需要判断前面一个数是否也等于target，如果是就继续 在前半区域找，
                //如果不是，那么mid就是第一个出现的值
                if(mid==0||a[mid-1]!=target) return mid;
                else end = mid -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,6,7,7,7,9};
        System.out.println(search(a,7));
    }
}
