package cn.yang.learning.notes.note2025.二叉树;

public class 二叉树中的最大路径和 {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSum2(root);
        return maxSum;
    }

    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxPathSum2(root.left);
        int right = maxPathSum2(root.right);

        if (left <= 0 && right <= 0) {
            maxSum = Math.max(maxSum, root.val);
        } else {
            maxSum = Math.max(maxSum, root.val+(left<=0?0:left)+(right<=0?0:right));
        }

        if (left <= 0 && right <= 0) {
            return root.val;
        } else {
            return root.val + Math.max(left, right);
        }
    }


}
