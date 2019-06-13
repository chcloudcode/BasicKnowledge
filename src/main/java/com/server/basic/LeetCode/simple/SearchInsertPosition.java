package com.server.basic.LeetCode.simple;

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if(nums[0]>target) return 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target) return i;
            if(nums[i]<target&&i+1<nums.length&&nums[i+1]>target) return i+1;
        }
        return nums.length;
    }

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        int[] nums = new int[]{1,3,5,7,9};
        int target = 6;
        System.out.println(s.searchInsert(nums,target));
    }
}
