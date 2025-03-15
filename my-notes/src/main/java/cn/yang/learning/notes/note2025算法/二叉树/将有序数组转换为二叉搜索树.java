package cn.yang.learning.notes.note2025算法.二叉树;

public class 将有序数组转换为二叉搜索树 {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }

        return sortedArrayToBST2(nums, 0, nums.length-1);
    }

    public TreeNode sortedArrayToBST2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 以下代码加不加都可以
//        if (left == right) {
//            return new TreeNode(nums[left]);
//        }


        int mid = left + (right-left)/2;
        TreeNode leftN = sortedArrayToBST2(nums, left, mid-1);
        TreeNode rightN = sortedArrayToBST2(nums, mid+1, right);

        TreeNode node = new TreeNode(nums[mid]);
        node.left = leftN;
        node.right = rightN;

        return node;
    }


}
