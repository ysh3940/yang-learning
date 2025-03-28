package cn.yang.learning.notes.note2025算法.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i=0; i<nums.length-2; ++i) {
            if (nums[i] > 0) {
                break;
            }

            if (i >0 && nums[i]==nums[i-1]) {
                continue;
            }

            int left = i+1;
            int right = nums.length-1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    ++left;
                } else if (sum > 0) {
                    --right;
                } else {
                    result.add(Arrays.asList(nums[i] , nums[left] , nums[right]));

                    while (left < right && nums[left] == nums[left+1]) {
                        ++left;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        --right;
                    }

                    ++left;
                    ++right;
                }
            }
        }

        return result;
    }


}
