package cn.yang.learning.notes.note2025算法.普通数组;

public class 最大子数组和 {

    public int maxSubArray(int[] nums) {
        int l = nums.length;
        int[] dp = new int[l];
        dp[0] = nums[0];
        int sum = nums[0];
        for (int i=1; i<l; ++i) {
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            sum = Math.max(sum, dp[i]);
        }

        return sum;
    }

}
