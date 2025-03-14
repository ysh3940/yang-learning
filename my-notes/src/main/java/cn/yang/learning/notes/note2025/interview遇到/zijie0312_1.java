package cn.yang.learning.notes.note2025.interview遇到;

public class zijie0312_1 {

    public static void main(String[] args) {
        test1(new int[]{2,2,3,4,5});
    }

    // [2,2,2,3,4,5] 搜搜222或234出现的次数
    public static int test1(int[] nums) {
        int len = nums.length;
        int left = 0;

        for (int right=1; right<len; ++right) {
            if ((right-left)==2) {
                if (match(nums, left, right)) {
                    print(nums, left, right);
                    left = right+1;
                } else {
                    ++left;
                }
            }
        }

        return 0;
    }

    public static boolean match(int[] arr1, int start, int end) {
        if (arr1[start] == arr1[start+1] && arr1[start+1] == arr1[end]) {
            return true;
        }

        if (arr1[start]+1 == arr1[start+1] && arr1[start+1]+1 == arr1[end]) {
            return true;
        }

        return false;
    }

    public static void print(int[] arr1, int start, int end) {
        for (int i=start; i<=end; ++i) {
            if (i ==end) {
                System.out.print(arr1[i]);
            } else {
                System.out.print(arr1[i]+"-");
            }

        }
        System.out.println();
    }


}
