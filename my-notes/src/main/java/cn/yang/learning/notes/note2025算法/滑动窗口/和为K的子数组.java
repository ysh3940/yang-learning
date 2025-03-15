package cn.yang.learning.notes.note2025算法.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class 和为K的子数组 {

    public int subarraySum1(int[] nums, int k) {
        int count = 0;

        for (int i=0; i<nums.length; i++) {
            int sum=0;
            for (int j=i; j<nums.length; ++j) {
                sum += nums[j];
                if (sum == k) {
                    ++count;
                }

            }
        }

        return count;
    }

    // 前缀和的思路解决
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int preSum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<nums.length; ++i) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }

            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }



}
