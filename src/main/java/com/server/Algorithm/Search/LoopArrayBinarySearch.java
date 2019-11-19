package com.server.Algorithm.Search;

/**
 * 循环数组中 寻找给定值
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class LoopArrayBinarySearch {

    //    public static  int search(int[] nums, int target) {
//        int index = 0;
//        while(index<nums.length-1){
//            if(nums[index]>nums[index+1]){
//                index++;
//                break;
//            }
//            index++;
//        }
//        int result = -1;
//        if(index==0){
//            return BinarySearch(nums,0,nums.length-1,target);
//        }else{
//            if(nums[0]<=target&&target<=nums[index-1]){
//                result= BinarySearch(nums,0,index-1,target);
//            }
//            if (target >= nums[index]&&target<=nums[nums.length-1]) {
//                result = BinarySearch(nums,index,nums.length-1,target);
//            }
//        }
//        return result;
//    }
//
//    private static int BinarySearch(int[] a, int start,int end ,int target) {
//        while(start<=end){
//            int mid = (start+end)/2;
//            if(a[mid] == target) return mid;
//            if(a[mid]>target){
//                end = mid-1;
//            }else{
//                start=mid+1;
//            }
//        }
//        return -1;
//    }
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {  //左边升序
                if (target >= nums[left] && target <= nums[mid]) {//在左边范围内
                    right = mid - 1;
                } else {//只能从右边找
                    left = mid + 1;
                }

            } else { //右边升序
                if (target >= nums[mid] && target <= nums[right]) {//在右边范围内
                    left = mid + 1;
                } else {//只能从左边找
                    right = mid - 1;
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] a = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(a, 7));

    }
}
