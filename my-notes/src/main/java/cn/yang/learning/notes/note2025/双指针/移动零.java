package cn.yang.learning.notes.note2025.双指针;

public class 移动零 {

    public void moveZeroes(int[] nums) {
        int left = 0;
        for (int i=0; i<nums.length; ++i) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                ++left;
            }
        }
    }

}
