package com.server.Algorithm.Search;

/**
 * 简单的 二分查找，无重复值，循环实现
 */
public class SimpleBinarySearch {

    public static int search(int[] a,int target){
        int start=0,end=a.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(a[mid] == target) return mid;
            if(a[mid]>target){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }

    //优化点：如果 start 和 end 都比较大的话 很容易造成 之和溢出 换成 start+(end-start)/2
    //也可以将 除以2操作换成位运算，start+((end-start)>>1)
    public static int search2(int[] a,int target){
        int start=0,end=a.length-1;
        while(start<=end){
            int mid = start + ((end - start )>>1);
            if(a[mid] == target) return mid;
            if(a[mid]>target){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,3,4,6,6,8,9};
        System.out.println(search2(a,6));
    }

}
