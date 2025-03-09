package cn.yang.learning.notes.note2025.普通数组;

public class 缺失的第一个正数 {

    public int firstMissingPositive(int[] nums) {
        int l = nums.length;
        for (int i=0; i<l; ++i) {
            if (nums[i] <=0 || nums[i] > l) {
                nums[i] = l+1;
            }
        }

        for (int i=0; i<l; ++i) {
            int temp = Math.abs(nums[i]);
            if (temp <= l) {
                nums[temp] = -Math.abs(nums[temp]);
            }
        }

        for (int i=0; i<l; ++i) {
            if (nums[i] > 0) {
                return i+1;
            }
        }

        return l+1;
    }


}
