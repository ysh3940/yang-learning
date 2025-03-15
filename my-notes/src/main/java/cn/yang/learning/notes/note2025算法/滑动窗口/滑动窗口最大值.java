package cn.yang.learning.notes.note2025算法.滑动窗口;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 滑动窗口最大值 {

    public Integer[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k== 0) {
            return new Integer[0];
        }

        List<Integer> res = new ArrayList<>();
        for (int i=0; i<nums.length-k; ++i) {
            int maxN = nums[i];
            for (int j=i; j<i+k; ++j) {
                maxN = Math.max(maxN, nums[j]);
            }
            res.add(maxN);
        }

        return res.toArray(new Integer[0]);
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k== 0) {
            return new int[0];
        }
        int[] res = new int[n-k+1];

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i=0; i<n; ++i) {
            if (queue.isEmpty() == false && queue.peekFirst()+k < i+1) {
                queue.pollFirst();
            }

            while (queue.isEmpty() == false && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offerLast(i);
            if (i+1 > k) {
                res[i-k+1] = nums[queue.pollFirst()];
            }
        }

        return res;
    }

}
