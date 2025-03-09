package cn.yang.learning.notes.note2025.哈希表;

import java.util.HashSet;
import java.util.Set;

public class 最长连续序列 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> all = new HashSet<>();
        for (int i=0; i<nums.length; ++i) {
            all.add(nums[i]);
        }

        int count = 0;
        for (int num : nums) {
            if (all.contains(num-1) == false) {
                int currNum = num;
                int currCount = 1;
                while (all.contains(currNum+1)) {
                    currNum++;
                    currCount++;
                }

                count = Math.max(count, currCount);
            }
        }


        return 0;
    }

}
