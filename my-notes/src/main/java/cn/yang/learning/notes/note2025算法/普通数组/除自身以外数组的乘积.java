package cn.yang.learning.notes.note2025算法.普通数组;

public class 除自身以外数组的乘积 {

    public int[] productExceptSelf(int[] nums) {
        int l = nums.length;
        int[] leftP = new int[l];
        int[] rightP = new int[l];
        int[] res = new int[l];

        leftP[0]=1;
        for (int i=1; i<l; ++i) {
            leftP[i] = leftP[i-1] * nums[i-1];
        }

        rightP[l-1]=1;
        for (int i=l-2; i>=0; --i) {
            rightP[i] = rightP[i-1] * nums[i+1];
        }

        for (int i=0; i<l; ++i) {
            res[i] = leftP[i] * rightP[i];
        }


        return res;
    }


}
