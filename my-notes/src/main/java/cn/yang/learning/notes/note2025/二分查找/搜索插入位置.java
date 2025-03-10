package cn.yang.learning.notes.note2025.二分查找;

public class 搜索插入位置 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return left;
    }

}
