package cn.yang.learning.notes.note2025算法.双指针;

public class 接雨水 {

    public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;

        int leftMax = 0;
        int rightMax = 0;

        int sum = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                sum += leftMax - height[left];
                ++left;
            } else {
                sum += rightMax - height[right];
                ++right;
            }

        }

        return sum;
    }


}
