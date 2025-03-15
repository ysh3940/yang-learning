package cn.yang.learning.notes.note2025算法.双指针;

public class 盛最多水的容器 {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxVal = 0;

        while (left < right) {
            int currVal = (right-left) * Math.min(height[left], height[right]);
            maxVal = Math.max(currVal, maxVal);
            if (height[left] < height[right]) {
                ++left;
            } else {
                ++right;
            }
        }

        return maxVal;
    }


}
