package com.software.LeetCode.simple;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *  输入: [-2,1,-3,4,-1,2,1,-5,4],
 *  输出: 6
 *  解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaximumSubarray {

    //低效的写法
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        for(int i =0;i<nums.length;i++){
            int sum = nums[i];
            if(sum>maxSum) maxSum=sum;
            for(int j = i+1;j<nums.length;j++){
                sum+=nums[j];
                if(sum>maxSum) maxSum=sum;
            }
        }
        return maxSum;
    }

    //O(n)写法
    public int maxSubArray1(int[] nums){
        int max = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            sum = sum<0 ? nums[i] : nums[i]+sum;
            if (sum>max){
                max = sum;
            }
        }
        return max;
    }


    //分治法
    public int maxSubArray2(int[] nums) {
        return divide(nums,0,nums.length-1);
    }
    public int divide(int[] nums,int start,int end){
        if(start>=end) return nums[start];//大于的情况就当是中间情况了
        int mid=(start+end)/2;
        int maxleft=divide(nums,start,mid-1);//左边最大和
        int maxright=divide(nums,mid+1,end);
        //计算包含中间的连续最大和
        int midl=nums[mid];
        for(int temp=mid-1,sum=nums[mid];temp>=start;temp--){//计算左边
            sum+=nums[temp];
            midl=midl>sum?midl:sum;
        }
        int midr=midl;
        for(int temp=mid+1,sum=midr;temp<=end;temp++){//计算右边
            sum+=nums[temp];
            midr=midr>sum?midr:sum;
        }
        return Math.max(maxleft,Math.max(maxright,midr));//最大子串在左边，最大子串在右边，或者包含中间那个数
    }

    public static void main(String[] args) {
        MaximumSubarray m = new MaximumSubarray();
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(m.maxSubArray1(arr));
    }
}
