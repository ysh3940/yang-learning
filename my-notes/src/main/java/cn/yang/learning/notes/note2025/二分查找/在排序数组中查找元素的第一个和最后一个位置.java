package cn.yang.learning.notes.note2025.二分查找;

public class 在排序数组中查找元素的第一个和最后一个位置 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1,-1};
        }

        int left = 0;
        int right = nums.length-1;

        int a = -1;

        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] == target) {
                a = mid;
                break;
            } else if (nums[mid] < target){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        if (a == -1) {
            return new int[]{-1,-1};
        }

        int b = a;
        int c = a;
        for (int i=b-1; i>=0; --i) {
            if (nums[i]==target) {
                b = i;
            } else {
                break;
            }
        }
        for (int i=c+1; i<=nums.length-1; ++i) {
            if (nums[i]==target) {
                c = i;
            } else {
                break;
            }
        }

        return new int[]{b, c};
    }


}
