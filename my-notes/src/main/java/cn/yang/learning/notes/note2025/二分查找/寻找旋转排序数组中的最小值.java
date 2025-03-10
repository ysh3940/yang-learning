package cn.yang.learning.notes.note2025.二分查找;

public class 寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int left = 0;
        int right = nums.length-1;

        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right-left)/2;
            min = Math.min(min, nums[mid]);

            if (nums[left] <= nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid+1;
            } else {
                min = Math.min(min, nums[mid+1]);
                right = mid;
            }
        }

        return min;
    }

}
