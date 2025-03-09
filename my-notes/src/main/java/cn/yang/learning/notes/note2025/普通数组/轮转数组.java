package cn.yang.learning.notes.note2025.普通数组;

public class 轮转数组 {

    public void rotate(int[] nums, int k) {
        int l = nums.length;
        k = k % l;

        res(nums, 0,l-1);
        res(nums, 0, k-1);
        res(nums, k,l-1);
    }

    private void res(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            ++start;
            --end;
        }


    }


}
